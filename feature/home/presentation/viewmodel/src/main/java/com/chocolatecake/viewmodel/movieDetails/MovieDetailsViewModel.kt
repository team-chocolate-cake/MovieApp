package com.chocolatecake.viewmodel.movieDetails

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RatingEntity

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
    MovieDetailsListener ,MediaListener , PeopleListener  {

    private val movieId = savedStateHandle.get<Int>("movieId") ?:502356

    init {
        _state.update { it.copy(isLoading = true) }
        getMovieDetails(movieId)
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
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    private fun onSuccessMovieDetails(movieDetails: MovieDetailsEntity) {
        _state.update {
            it.copy(
                id = movieDetails.id,
                movieUiState = MovieDetailsItem.Upper(
                    UpperUiState(
                        id = movieDetails.id,
                        backdropPath = movieDetails.backdropPath,
                        genres = movieDetails.genres,
                        title = movieDetails.title,
                        overview = movieDetails.overview,
                        voteAverage = movieDetails.voteAverage?.toFloat()?.div(2f),
                        videos = movieDetails.videos?.results?.map { it.key!! },
                    )
                ),
                recommendedUiState = MovieDetailsItem.Recommended(
                    movieDetails.recommendations?.recommendedMovies?.map {
                        MediaVerticalUIState(
                            id = it?.id ?:0,
                            rate = it?.voteAverage?:0.0,
                            imageUrl = it?.backdropPath?:"",
                        )
                    },
                ),
                reviewUiState = MovieDetailsItem.Reviews(
                    movieDetails.reviewEntities?.map {
                        ReviewUiState(
                            name = it.name,
                            avatar_path = it.avatar_path,
                            content = it.content,
                            created_at = it.created_at
                        )
                    }
                ),
                castUiState = MovieDetailsItem.People(
                    movieDetails.credits?.cast?.map {
                        PeopleUIState(
                            id = it?.id?:0,
                            name = it?.name?:"",
                            imageUrl = it?.profilePath?:""
                        )
                    }
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

    private fun onSuccessRating(ratingEntity: RatingEntity) {
        //todo
        sendEvent(MovieDetailsUiEvent.onSuccessRateEvent(ratingEntity.statusMessage))

    }


    override fun onClickPeople(id: Int) {
        sendEvent(MovieDetailsUiEvent.PeopleEvent(id))
    }


    override fun onClickPlayTrailer(keys: List<String>) {
        sendEvent(MovieDetailsUiEvent.PlayVideoEvent(keys))
    }


    override fun onClickRate(id: Int) {
        sendEvent(MovieDetailsUiEvent.RateMovieEvent(id))
    }

    override fun onClickBackButton() {
        sendEvent(MovieDetailsUiEvent.OnClickBack)
    }

    override fun onClickSaveButton(id: Int) {
        sendEvent(MovieDetailsUiEvent.SaveToEvent(id))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(MovieDetailsUiEvent.RecommendedMovieEvent(id))
    }

}