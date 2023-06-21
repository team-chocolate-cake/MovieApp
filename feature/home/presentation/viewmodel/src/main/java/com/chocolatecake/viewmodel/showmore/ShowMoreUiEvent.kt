package com.chocolatecake.viewmodel.showmore

sealed interface ShowMoreUiEvent {
    data class NavigateToMovieDetails(val itemId: Int) : ShowMoreUiEvent
    object BackNavigate : ShowMoreUiEvent
    data class ShowSnackBar(val messages: String) : ShowMoreUiEvent

}