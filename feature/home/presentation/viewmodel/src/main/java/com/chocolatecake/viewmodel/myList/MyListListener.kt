package com.chocolatecake.viewmodel.myList
import com.chocolatecake.bases.BaseInteractionListener

interface MyListListener : BaseInteractionListener {
    fun onClickFavoriteList(itemId: Int , listType: String = "FAVORITE")
    fun onClickWatchlist(itemId: Int , listType: String ="WATCHLIST")
    fun onClickItem(listId: Int , listType: String = "DEFAULT")
}