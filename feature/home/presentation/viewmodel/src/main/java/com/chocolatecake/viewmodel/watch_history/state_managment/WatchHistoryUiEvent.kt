package com.chocolatecake.viewmodel.watch_history.state_managment

sealed interface WatchHistoryUiEvent {
    data class NavigateToMovieDetails(
        val movieId: Int
    ) : WatchHistoryUiEvent

    data class ShowSnackBar(
        val message: String
    ) : WatchHistoryUiEvent

    data class Error(
        val message: String
    ) : WatchHistoryUiEvent

}