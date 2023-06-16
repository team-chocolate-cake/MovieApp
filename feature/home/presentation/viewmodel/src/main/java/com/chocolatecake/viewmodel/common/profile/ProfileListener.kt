package com.chocolatecake.viewmodel.common.profile

import com.chocolatecake.bases.BaseInteractionListener

interface ProfileListener : BaseInteractionListener {
    fun onClickFavorites(itemId: Int)
    fun onClickWatchlist(itemId: Int)
    fun onClickWatchHistory(itemId: Int)
    fun onClickMyLists(itemId: Int)
    fun onClickRating(itemId: Int)
    fun onClickPopcornPuzzles(itemId: Int)
    fun onClickTheme(itemId: Int)
    fun onClickLogout(itemId: Int)
}