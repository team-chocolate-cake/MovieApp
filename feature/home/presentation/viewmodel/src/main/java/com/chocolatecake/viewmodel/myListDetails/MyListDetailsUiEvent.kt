package com.chocolatecake.viewmodel.myListDetails

import com.chocolatecake.viewmodel.myList.MyListUiEvent


sealed interface MyListDetailsUiEvent {
    data class NavigateToMovieDetails(val movieId: Int) : MyListDetailsUiEvent

    data class ShowSnackBar(val message:String): MyListDetailsUiEvent

    object OnClickBack : MyListDetailsUiEvent
}