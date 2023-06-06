package com.chocolatecake.movieapp.ui.search.ui_state

sealed interface SearchUiEvent{
    object FilterEvent: SearchUiEvent
    data class ApplyFilterEvent(val genre: Int): SearchUiEvent
    data class ShowSnackBar(val messages: String) : SearchUiEvent

}