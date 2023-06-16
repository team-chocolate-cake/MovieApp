package com.chocolatecake.viewmodel.movieDetails

sealed interface MovieDetailsUiEvent{

    data class SaveToEvent(val movieId: Int) : MovieDetailsUiEvent
    data class RateMovieEvent(val movieId: Int) : MovieDetailsUiEvent
    data class PlayVideoEvent(val youtubeKey: String) : MovieDetailsUiEvent
    object OnClickBack : MovieDetailsUiEvent
}
