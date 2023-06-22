package com.chocolatecake.viewmodel.myList

import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.search.SearchUiEvent

sealed interface MyListUiEvent {
    data class NavigateToListDetails(val listId: Int, val listType: String, val listName: String) :
        MyListUiEvent

    data class ShowSnackBar(val message: String) : MyListUiEvent
    object OpenCreateListBottomSheet: MyListUiEvent
    object ApplyCreateList: MyListUiEvent

    object OnClickBack : MyListUiEvent
}