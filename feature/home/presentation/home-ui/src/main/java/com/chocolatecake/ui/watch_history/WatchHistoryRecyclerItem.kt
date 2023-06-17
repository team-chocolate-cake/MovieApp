package com.chocolatecake.ui.watch_history

import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState


sealed class WatchHistoryRecyclerItem {
    data class CardRecyclerItem(val movie: MovieUiState) : WatchHistoryRecyclerItem()
    data class TitleRecyclerItem(val title: String) : WatchHistoryRecyclerItem()
}
