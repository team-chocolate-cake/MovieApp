package com.chocolatecake.viewmodel.myList
import com.chocolatecake.bases.BaseInteractionListener

interface MyListListener : BaseInteractionListener {
//    fun onClickFavoriteList(itemId: Int , listType: String = "favorite" , mediaType: String = "tv")
//    fun onClickWatchlist(itemId: Int , listType: String ="watchlist", mediaType: String = "tv")
    fun onClickItem(listId: Int , listType: String = "movie", listName: String = "favorite")

    fun onClickNewList()
}