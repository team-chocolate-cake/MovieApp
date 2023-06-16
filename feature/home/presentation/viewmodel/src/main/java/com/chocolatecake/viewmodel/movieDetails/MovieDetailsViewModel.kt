package com.chocolatecake.viewmodel.movieDetails

import android.util.Log
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity

import com.chocolatecake.usecase.movie_details.GetMovieDetailsUseCase
import com.chocolatecake.viewmodel.movieDetails.ui_state.CastUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsListener
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.RecommendedMoviesUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.ReviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<MovieDetailsUiState, MovieDetailsUiEvent>(MovieDetailsUiState()),
    MovieDetailsListener {


    init {
        _state.update { it.copy(isLoading = true) }
        getMovieDetails(502356)
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
                movieUiState = MovieUiState(
                    id = movieDetails.id,
                    backdropPath = movieDetails.backdropPath,
                    genres = movieDetails.genres,
                    title = movieDetails.title,
                    overview = movieDetails.overview,
                    voteAverage = movieDetails.voteAverage,
                    videos = movieDetails.videos?.results?.map { it.key },
                    ),
                recommendedUiState = MovieDetailsItem.Recommended(
                    movieDetails.recommendations?.recommendedMovies?.map {
                        RecommendedMoviesUiState(
                            id = it?.id,
                            voteAverage = it?.voteAverage,
                            backdropPath = it?.backdropPath,
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
                        CastUiState(
                            id = it?.id,
                            name = it?.name,
                            profilePath = it?.profilePath
                        )
                    }
                ),
                isLoading = false
            )
        }
    }

    override fun onClickPeople(itemId: Int) {
        Log.d("TAG", "People")
    }

    override fun onClickRecommendedMovie(itemId: Int) {
        Log.d("TAG", "Recommended")
    }

}