package com.chocolatecake.viewmodel.myList
import com.chocolatecake.bases.BaseInteractionListener

interface MyListListener : BaseInteractionListener {

    fun onClickItem(listId: Int , listType: String = "movie", listName: String = "favorite")

    fun onClickNewList()

    fun onClickBackButton()
    fun onClickShowDelete()
    fun onClickDelete(listId: Int)
}