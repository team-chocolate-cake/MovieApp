package com.chocolatecake.viewmodel.myList
sealed interface MyListUiEvent {
    data class NavigateToListDetails(val listId: Int ,val listType: String) : MyListUiEvent

    data class ShowSnackBar(val message:String): MyListUiEvent
}