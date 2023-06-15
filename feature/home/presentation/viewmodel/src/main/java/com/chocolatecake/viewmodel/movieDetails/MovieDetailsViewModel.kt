package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity

import com.chocolatecake.usecase.movie_details.GetMovieDetailsUseCase
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsListener
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieDetailsUseCase: GetMovieDetailsUseCase
) : BaseViewModel<MovieDetailsUiState, MovieDetailsUiEvent>(MovieDetailsUiState()) ,
    MovieDetailsListener {


    init {
        _state.update { it.copy(isLoading = true) }
        getMovieDetails(502356)
    }



    private fun getMovieDetails(movieId:Int) {
        tryToExecute(
            call = {movieDetailsUseCase(movieId)},
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
                movieDetailsEntity = movieDetails,
                isLoading = false
            )
        }
    }

}