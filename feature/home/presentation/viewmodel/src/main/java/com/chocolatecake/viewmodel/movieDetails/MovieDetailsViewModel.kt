package com.chocolatecake.viewmodel.movieDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.repository.NoNetworkThrowable
import com.chocolatecake.repository.UnauthorizedThrowable
import com.chocolatecake.usecase.common.AddToUserListUseCase
import com.chocolatecake.usecase.common.CreateUserListUseCase
import com.chocolatecake.usecase.common.GetUserListsUseCase
import com.chocolatecake.usecase.movie_details.AddToFavouriteUseCase
import com.chocolatecake.usecase.movie_details.AddToWatchList
import com.chocolatecake.usecase.movie_details.GetMovieDetailsUseCase
import com.chocolatecake.usecase.movie_details.GetRatingUseCase
import com.chocolatecake.viewmodel.common.listener.ChipListener
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.movieDetails.mapper.UserListsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val ratingUseCase: GetRatingUseCase,
    private val getUserListsUseCase: GetUserListsUseCase,
    private val addToUserListUseCase: AddToUserListUseCase,
    private val createUserListUseCase: CreateUserListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val addToWatchList: AddToWatchList,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<MovieDetailsUiState, MovieDetailsUiEvent>(MovieDetailsUiState()),
    MovieDetailsListener, MediaListener, PeopleListener, ChipListener {

    private val movieId = savedStateHandle.get<Int>("movieId")

    init {
        _state.update { it.copy(isLoading = true) }
        if (movieId != null) {
            getMovieDetails(movieId)
        } else {
            val errors = _state.value.onErrors.toMutableList()
            errors.add("There are a problem with MovieId")
            _state.update { it.copy(onErrors = errors, isLoading = false) }
        }
    }


    private fun getMovieDetails(movieId: Int) {
        tryToExecute(
            call = { movieDetailsUseCase(movieId) },
            onSuccess = ::onSuccessMovieDetails,
            onError = ::onError
        )
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        when (th) {
            is NoNetworkThrowable -> errors.add("noNetwork")
            is UnauthorizedThrowable -> errors.add("noNetwork")
            else -> errors.add(th.message.toString())
        }
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    private fun onSuccessMovieDetails(movieDetails: MovieDetailsEntity) {
        _state.update {
            it.copy(
                id = movieDetails.id,
                movieUiState = UpperUiState(
                    id = movieDetails.id,
                    backdropPath = movieDetails.backdropPath,
                    genres = movieDetails.genres,
                    title = movieDetails.title,
                    overview = movieDetails.overview,
                    voteAverage = movieDetails.voteAverage.toFloat().div(2f),
                    videos = movieDetails.videos.results.map { it.key },
                ),
                recommendedUiState = movieDetails.recommendations.recommendedMovies.map {
                    MediaVerticalUIState(
                        id = it.id,
                        rate = it.voteAverage,
                        imageUrl = it.backdropPath,
                    )
                },
                reviewUiState = movieDetails.reviewEntity.reviews.map {
                    ReviewUiState(
                        name = it.name,
                        avatar_path = it.avatar_path,
                        content = it.content,
                        created_at = it.created_at
                    )
                },
                castUiState =
                movieDetails.credits.cast.map {
                    PeopleUIState(
                        id = it.id,
                        name = it.name,
                        imageUrl = it.profilePath
                    )
                },
                reviewsDetails = ReviewDetailsUiState(
                    page = movieDetails.reviewEntity.page,
                    totalPages = movieDetails.reviewEntity.totalPages,
                    totalReviews = movieDetails.reviewEntity.totalResults
                ),
                isLoading = false
            )
        }
    }

    fun onRatingSubmit() {
        tryToExecute(
            call = { ratingUseCase(movieId!!, state.value.userRating) },
            onSuccess = ::onRatingSuccess,
            onError = ::onRatingError
        )
    }

    fun updateRatingUiState(rate: Float) {
        _state.update {
            it.copy(
                userRating = rate
            )
        }

    }

    //region user lists

    fun emptyUserLists() {
        _state.update {
            it.copy(userLists = emptyList())
        }
    }

    fun getUserLists() {
        tryToExecute(
            call = { getUserListsUseCase() },
            onSuccess = ::onGetUserListsUseCase,
            onError = {
                Log.i("lists", "something went wrong $it")
            }
        )
    }

    private fun onGetUserListsUseCase(userListsEntity: List<UserListEntity>) {
        val item = UserListsUiMapper().map(userListsEntity)
        _state.update {
            it.copy(
                userLists = item.userLists
            )
        }
        Log.i("lists", "user lists => ${state.value.userLists}")
    }

    fun onDone(listsId: List<Int>) {
        listsId.forEach { id ->
            tryToExecute(
                call = { addToUserListUseCase(id, movieId!!) },
                onSuccess = ::onDoneSuccess,
                onError = {
                    sendEvent(MovieDetailsUiEvent.OnDoneAdding("something went wrong 😔"))
                    Log.i("chip", "something went wrong")
                }
            )
        }
    }

    private fun onDoneSuccess(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.OnDoneAdding("adding was successful"))
    }

    fun createUserNewList(listName: String) {
        tryToExecute(
            call = { createUserListUseCase(listName) },
            onSuccess = ::onCreateUserNewList,
            onError = {
                sendEvent(MovieDetailsUiEvent.OnCreateNewList("something went wrong"))
            }
        )
    }

    fun addToFavourite() {
        tryToExecute(
            call = { addToFavouriteUseCase(movieId!!,"movie") },
            onSuccess = {
                sendEvent(MovieDetailsUiEvent.OnFavourite("added successfully"))
            },
            onError = {
                sendEvent(MovieDetailsUiEvent.OnFavourite("something went wrong"))
            }
        )
    }
    fun addToWatchlist() {
        tryToExecute(
            call = { addToWatchList(movieId!!,"movie") },
            onSuccess = {
                sendEvent(MovieDetailsUiEvent.OnWatchList("added successfully"))
            },
            onError = {
                sendEvent(MovieDetailsUiEvent.OnWatchList("something went wrong"))
            }
        )
    }

    private fun onCreateUserNewList(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.OnCreateNewList("New List Was Added Successfully"))
        getUserLists()
    }
    //endregion

    private fun onRatingSuccess(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.ApplyRating("rating was successfull 🥰"))
    }

    private fun onRatingError(error: Throwable) {
        sendEvent(MovieDetailsUiEvent.ApplyRating("something went wrong 🤔\nplease try again later."))
    }

    override fun onClickPeople(id: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToPeopleDetails(id))
    }


    override fun onClickPlayTrailer(keys: List<String>) {
        sendEvent(MovieDetailsUiEvent.PlayVideoTrailer(keys))
    }


    override fun onClickRate(id: Int) {
        sendEvent(MovieDetailsUiEvent.RateMovie(id))
    }

    override fun onClickBackButton() {
        sendEvent(MovieDetailsUiEvent.OnClickBack)
    }

    fun onClickSaveButton() {
        sendEvent(MovieDetailsUiEvent.SaveToList(movieId!!))
    }

    override fun onClickShowMore(movieId: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToShowMore(movieId))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToMovie(id))
    }

    fun tryAgain(movieId: Int) {
        _state.update { it.copy(isLoading = true, onErrors = emptyList()) }
        getMovieDetails(movieId)
    }

    override fun onChipClick(id: Int) {
        val updatedList = state.value.userSelectedLists.toMutableList()
        if (updatedList.remove(id)) Unit else updatedList.add(id)

        _state.update {
            it.copy(
                userSelectedLists = updatedList
            )
        }
        Log.i("chip", "selected lists => ${state.value.userSelectedLists}")
    }

}