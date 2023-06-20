package com.chocolatecake.viewmodel.movieDetails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RatingResponseEntity
import com.chocolatecake.repository.NoNetworkThrowable
import com.chocolatecake.repository.UnauthorizedThrowable

import com.chocolatecake.usecase.movie_details.GetMovieDetailsUseCase
import com.chocolatecake.usecase.movie_details.GetRatingUseCase
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase,
    private val ratingUseCase: GetRatingUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<MovieDetailsUiState, MovieDetailsUiEvent>(MovieDetailsUiState()),
    MovieDetailsListener, MediaListener, PeopleListener {

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
            else ->errors.add(th.message.toString())
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
                        id = it.id ,
                        rate = it.voteAverage ,
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
                        id = it.id ,
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

    fun onRatingSubmit(rating: Float, movieId: Int) {
        tryToExecute(
            call = { ratingUseCase(movieId, rating) },
            onSuccess = ::onSuccessRating,
            onError = ::onError
        )
    }

    private fun onSuccessRating(ratingResponseEntity: RatingResponseEntity) {
        sendEvent(MovieDetailsUiEvent.ShowSnackBarRating(ratingResponseEntity.statusMessage))
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

    override fun onClickSaveButton(id: Int) {
        sendEvent(MovieDetailsUiEvent.SaveToList(id))
    }

    override fun onClickShowMore(movieId: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToShowMore(movieId))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(MovieDetailsUiEvent.NavigateToMovie(id))
    }
    fun tryAgain(movieId: Int){
        _state.update { it.copy(isLoading = true , onErrors = emptyList()) }
        getMovieDetails(movieId)
    }

}