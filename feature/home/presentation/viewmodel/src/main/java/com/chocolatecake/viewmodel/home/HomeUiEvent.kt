package com.chocolatecake.viewmodel.home

sealed interface HomeUiEvent{
    data class MovieEvent(val itemId: Int) : HomeUiEvent

    object ClickShowMore : HomeUiEvent
}