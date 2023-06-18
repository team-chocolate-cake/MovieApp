package com.chocolatecake.viewmodel.watch_history.state_managment


data class WatchHistoryUiState(
    val searchInput: String = "",
    val movies: List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
    var tempMovie: MovieUiState? = null
)

