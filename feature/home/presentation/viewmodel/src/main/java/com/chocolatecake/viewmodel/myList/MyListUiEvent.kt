package com.chocolatecake.viewmodel.myList

sealed interface MyListUiEvent {
    data class NavigateToListDetails(val listId: Int, val listType: String, val listName: String) :
        MyListUiEvent

    data class ShowSnackBar(val message: String) : MyListUiEvent
    data class OnCreateNewList(val message:String):MyListUiEvent
    object OpenCreateListBottomSheet: MyListUiEvent
    object ApplyCreateList: MyListUiEvent

    object OnClickBack : MyListUiEvent
    data class ShowConfirmDeleteDialog(val listId: Int, val listName: String): MyListUiEvent
}