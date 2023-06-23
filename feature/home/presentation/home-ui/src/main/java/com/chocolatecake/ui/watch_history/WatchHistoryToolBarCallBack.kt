package com.chocolatecake.ui.watch_history

interface WatchHistoryToolBarCallBack {
    fun setSearchQueryState(query: CharSequence?)
    fun onBackButtonPressed()
}