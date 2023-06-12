package com.chocolatecake.movieapp.ui.search

sealed interface SearchUiEvent{
    object FilterEvent: SearchUiEvent
    data class ApplyFilterEvent(val genre: Int): SearchUiEvent
    data class ShowSnackBar(val messages: String) : SearchUiEvent
    data class ClickMediaEvent(val media: String) : SearchUiEvent

}