package com.chocolatecake.viewmodel.movieDetails


sealed interface MovieDetailsUiEvent{

    data class SaveToList(val movieId: Int) : MovieDetailsUiEvent
    data class NavigateToPeopleDetails(val itemId: Int) : MovieDetailsUiEvent
    data class RateMovie(val movieId: Int) : MovieDetailsUiEvent
    data class NavigateToMovie(val movieId: Int) : MovieDetailsUiEvent
    data class PlayVideoTrailer(val youtubeKeys: List<String>) : MovieDetailsUiEvent
    data class NavigateToShowMore(val movieId: Int) : MovieDetailsUiEvent
    object OnClickBack : MovieDetailsUiEvent
    data class ApplyRating(val message: String) : MovieDetailsUiEvent
}
