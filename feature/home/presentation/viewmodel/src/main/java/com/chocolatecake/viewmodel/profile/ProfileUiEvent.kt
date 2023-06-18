package com.chocolatecake.viewmodel.profile

sealed interface ProfileUiEvent{
    object FavoriteEvent : ProfileUiEvent
    object WatchlistEvent : ProfileUiEvent
    object WatchHistoryEvent : ProfileUiEvent
    object MyListsEvent : ProfileUiEvent
    object RatingEvent : ProfileUiEvent
    object PopcornPuzzlesEvent : ProfileUiEvent
    object ThemeEvent : ProfileUiEvent
    object LogoutEvent : ProfileUiEvent
}
