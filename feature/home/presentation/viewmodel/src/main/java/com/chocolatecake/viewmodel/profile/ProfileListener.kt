package com.chocolatecake.viewmodel.profile

interface ProfileListener {
    fun onClickFavorite()
    fun onClickWatchlist()
    fun onClickWatchHistory()
    fun onClickMyLists()
    fun onClickPopcornPuzzles()
    fun onClickSwitchTheme()
    fun onClickLogout()
    fun onUserNotLoggedIn()
}