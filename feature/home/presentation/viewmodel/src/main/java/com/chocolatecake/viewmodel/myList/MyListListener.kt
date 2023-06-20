package com.chocolatecake.viewmodel.myList
import com.chocolatecake.bases.BaseInteractionListener

interface MyListListener : BaseInteractionListener {
    fun onClickFavoriteList(itemId: Int , listType: String = "favorite")
    fun onClickWatchlist(itemId: Int , listType: String ="watchlist")
    fun onClickItem(listId: Int , listType: String = "default")
}