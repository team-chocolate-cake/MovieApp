package com.chocolatecake.viewmodel.watch_history.state_managment

import com.chocolatecake.bases.BaseInteractionListener

interface WatchHistoryOnEventListeners : BaseInteractionListener {
    fun onCardClickListener(id:Int)
    fun onCardSwipeListener()

}