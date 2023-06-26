package com.chocolatecake.viewmodel.movieDetails


sealed interface MovieDetailsUiEvent{

    data class SaveToList(val movieId: Int) : MovieDetailsUiEvent
    data class NavigateToPeopleDetails(val itemId: Int) : MovieDetailsUiEvent
    data class RateMovie(val movieId: Int) : MovieDetailsUiEvent
    data class NavigateToMovie(val movieId: Int) : MovieDetailsUiEvent
    data class PlayVideoTrailer(val videoKey: String) : MovieDetailsUiEvent
    data class NavigateToShowMore(val movieId: Int) : MovieDetailsUiEvent
    object OnClickBack : MovieDetailsUiEvent
    data class ApplyRating(val message: String) : MovieDetailsUiEvent
    data class OnDoneAdding(val message: String):MovieDetailsUiEvent
    data class OnCreateNewList(val message:String):MovieDetailsUiEvent
    data class OnFavourite(val message: String):MovieDetailsUiEvent
    data class OnWatchList(val message: String):MovieDetailsUiEvent
}
