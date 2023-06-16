package com.chocolatecake.viewmodel.movieDetails.ui_state

import com.chocolatecake.viewmodel.home.HomeUiEvent

sealed interface MovieDetailsUiEvent{

    data class SaveToEvent(val movieId: Int) : MovieDetailsUiEvent
    data class RateMovieEvent(val movieId: Int) : MovieDetailsUiEvent
    data class PlayVideoEvent(val youtubeKey: String) : MovieDetailsUiEvent
    object OnClickBack : MovieDetailsUiEvent
}
