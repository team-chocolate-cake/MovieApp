package com.chocolatecake.viewmodel.search

sealed interface SearchUiEvent{
    object OpenFilterBottomSheet: SearchUiEvent
    data class ApplyFilter(val genre: Int): SearchUiEvent
    data class ShowSnackBar(val messages: String) : SearchUiEvent
    data class NavigateToMovie(val movieId: Int): SearchUiEvent
    data class NavigateToTv(val tvId: Int): SearchUiEvent
    data class NavigateToPeople(val peopleId: Int): SearchUiEvent
    object ShowMovieResult: SearchUiEvent
    object ShowTvResult: SearchUiEvent
    object ShowPeopleResult: SearchUiEvent
}