package com.chocolatecake.viewmodel.watch_history

import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState


sealed class WatchHistoryRecyclerItem {
    data class MovieCard(val movie: MovieUiState) : WatchHistoryRecyclerItem()
    data class Title(val title: String) : WatchHistoryRecyclerItem()
    object SearchBar : WatchHistoryRecyclerItem()
}
