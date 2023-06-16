package com.chocolatecake.viewmodel.movieDetails


sealed interface MovieDetailsUiEvent{

    data class SaveToEvent(val movieId: Int) : MovieDetailsUiEvent
    data class PeopleEvent(val itemId: Int) : MovieDetailsUiEvent
    data class RateMovieEvent(val movieId: Int) : MovieDetailsUiEvent
    data class RecommendedMovieEvent(val movieId: Int) : MovieDetailsUiEvent
    data class PlayVideoEvent(val youtubeKeys: List<String>) : MovieDetailsUiEvent
    object OnClickBack : MovieDetailsUiEvent
}
