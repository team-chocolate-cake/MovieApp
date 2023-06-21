package com.chocolatecake.viewmodel.watch_history.state_managment

import com.chocolatecake.viewmodel.watch_history.WatchHistoryRecyclerItem


data class WatchHistoryUiState(
    val searchInput: String = "",
    val movies: List<WatchHistoryRecyclerItem> = emptyList(),
    val isLoading: Boolean = false,
    val deletedMovie: MovieUiState? = null,
    val deletedTitle: String? = null,
    val snackBarUndoPressed: Boolean? = null,
    val swipePosition: Int? = null
)

