package com.chocolatecake.viewmodel.movieDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.entities.MovieInWatchHistoryEntity
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
import com.chocolatecake.usecase.movie_details.CheckIsLoginedOrNotUseCase
import com.chocolatecake.usecase.movie_details.GetMovieDetailsUseCase
import com.chocolatecake.usecase.movie_details.GetRatingUseCase
import com.chocolatecake.usecase.watch_history.InsertMovieToWatchHistoryUseCase
import com.chocolatecake.viewmodel.common.listener.ChipListener
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.movieDetails.mapper.CastUiStateMapper
import com.chocolatecake.viewmodel.movieDetails.mapper.RecommendedUiStateMapper
import com.chocolatecake.viewmodel.movieDetails.mapper.ReviewDetailsUiStateMapper
import com.chocolatecake.viewmodel.movieDetails.mapper.ReviewsUiStateMapper
import com.chocolatecake.viewmodel.movieDetails.mapper.UpperUiStateMapper
import com.chocolatecake.viewmodel.movieDetails.mapper.UserListsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.Date
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
    private val savedStateHandle: SavedStateHandle,
    private val insertMovieToWatchHistoryUseCase: InsertMovieToWatchHistoryUseCase,
    private val checkIsLoginedOrNotUseCase: CheckIsLoginedOrNotUseCase,
    private val recommendedUiStateMapper: RecommendedUiStateMapper,
    private val upperUiStateMapper: UpperUiStateMapper,
    private val reviewsUiStateMapper: ReviewsUiStateMapper,
    private val castUiStateMapper: CastUiStateMapper,
    private val reviewDetailsUiStateMapper: ReviewDetailsUiStateMapper,
    private val stringsRes: StringsRes
) : BaseViewModel<MovieDetailsUiState, MovieDetailsUiEvent>(MovieDetailsUiState()),
    MovieDetailsListener, MediaListener, PeopleListener, ChipListener {

    private val movieId = savedStateHandle.get<Int>("movieId")

    init {
        _state.update { it.copy(isLoading = true, isLogined = checkIsLoginedOrNotUseCase()) }
        if (movieId != null) {
            _state.update { it.copy(id = movieId) }
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
        when (th) {
            is NoNetworkThrowable -> errors.add(stringsRes.someThingError)
            is UnauthorizedThrowable -> errors.add(stringsRes.someThingError)
            else -> errors.add(th.message.toString())
        }
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    private fun onSuccessMovieDetails(movieDetails: MovieDetailsEntity) {
        _state.update {
            it.copy(
                id = movieDetails.id,
                movieUiState = upperUiStateMapper.map(movieDetails),
                recommendedUiState = recommendedUiStateMapper.map(movieDetails.recommendations.recommendedMovies) ,
                reviewUiState = reviewsUiStateMapper.map(movieDetails.reviewEntity.reviews),
                castUiState = castUiStateMapper.map(movieDetails.credits.cast),
                reviewsDetails = reviewDetailsUiStateMapper.map(movieDetails),
                isLoading = false,
                onErrors = emptyList()
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                insertMovieToWatchHistoryUseCase(movieDetails.toHistoryEntity())
            } catch (th: Throwable) {
                onError(th)
            }
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
                sendEvent(MovieDetailsUiEvent.ShowSnackBar(stringsRes.someThingError))
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
    }

    fun onDone(listsId: List<Int>) {
        listsId.forEach { id ->
            tryToExecute(
                call = { addToUserListUseCase(id, movieId!!) },
                onSuccess = ::onDoneSuccess,
                onError = {
                    sendEvent(MovieDetailsUiEvent.ShowSnackBar(stringsRes.duplicateEntity))
                }
            )
        }
    }

    private fun onDoneSuccess(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.OnDoneAdding(statusEntity.statusMessage))
    }

    fun createUserNewList(listName: String) {
        tryToExecute(
            call = { createUserListUseCase(listName) },
            onSuccess = ::onCreateUserNewList,
            onError = {
                sendEvent(MovieDetailsUiEvent.OnCreateNewList(stringsRes.someThingError))
            }
        )
    }

    fun addToFavourite() {
        tryToExecute(
            call = { addToFavouriteUseCase(movieId!!, "movie") },
            onSuccess = {
                sendEvent(MovieDetailsUiEvent.OnFavourite(stringsRes.addSuccessfully))
            },
            onError = {
                sendEvent(MovieDetailsUiEvent.OnFavourite(stringsRes.someThingError))
            }
        )
    }

    fun addToWatchlist() {
        tryToExecute(
            call = { addToWatchList(movieId!!, "movie") },
            onSuccess = {
                sendEvent(MovieDetailsUiEvent.OnWatchList(stringsRes.addSuccessfully))
            },
            onError = {
                sendEvent(MovieDetailsUiEvent.OnWatchList(stringsRes.someThingError))
            }
        )
    }

    private fun onCreateUserNewList(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.OnCreateNewList(stringsRes.newListAddSuccessFully))
        getUserLists()
    }
    //endregion

    private fun onRatingSuccess(statusEntity: StatusEntity) {
        sendEvent(MovieDetailsUiEvent.ApplyRating(stringsRes.ratingAddSuccessFully))
    }

    private fun onRatingError(error: Throwable) {
        sendEvent(MovieDetailsUiEvent.ApplyRating(stringsRes.someThingErrorWhenAddRating))
    }

    override fun onClickPeople(id: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToPeopleDetails(id))
    }


    override fun onClickPlayTrailer() {
        sendEvent(MovieDetailsUiEvent.PlayVideoTrailer(state.value.movieUiState.videoKey))
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
        _state.update {
            it.copy(
                userSelectedLists = (it.userSelectedLists + id).distinct()
            )
        }
    }
}

private fun MovieDetailsEntity.toHistoryEntity(): MovieInWatchHistoryEntity {
    return MovieInWatchHistoryEntity(
        id = id,
        posterPath = backdropPath,
        title = title,
        voteAverage = voteAverage,
        description = overview,
        dateWatched = Date(),
        year = year.takeIf { it.isNotBlank() }?.split("-")?.get(0)?.toInt() ?: 1911
    )
}
