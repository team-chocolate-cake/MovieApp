package com.chocolatecake.viewmodel.watch_history.state_managment

import com.chocolatecake.viewmodel.watch_history.WatchHistoryRecyclerItem


data class WatchHistoryUiState(
    val searchInput: String = "",
    val movies: List<WatchHistoryRecyclerItem> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
    var tempMovie: MovieUiState? = null,
    var snackBarUndoPressed: Boolean? = null,
    var swipePosition: Int? = null
)

