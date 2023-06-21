package com.chocolatecake.viewmodel.myList

sealed interface MyListUiEvent {
    data class NavigateToListDetails(val listId: Int, val listType: String, val mediaType: String) :
        MyListUiEvent

    data class ShowSnackBar(val message: String) : MyListUiEvent
}