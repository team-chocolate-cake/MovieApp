package com.chocolatecake.viewmodel.profile

sealed interface ProfileUiEvent{
    object FavoriteEvent : ProfileUiEvent
    object WatchlistEvent : ProfileUiEvent
    object WatchHistoryEvent : ProfileUiEvent
    object MyListsEvent : ProfileUiEvent
    object PopcornPuzzlesEvent : ProfileUiEvent
    object LogoutEvent : ProfileUiEvent
}
