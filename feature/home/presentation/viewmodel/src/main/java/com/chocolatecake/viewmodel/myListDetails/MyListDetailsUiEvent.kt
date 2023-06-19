package com.chocolatecake.viewmodel.myListDetails

//sealed interface MyListDetailsUiEvent {
//    object BackEvent : MyListDetailsUiEvent
//    data class ClickItemEvent(val itemId: Int) : MyListDetailsUiEvent
//}

sealed interface MyListDetailsUiEvent {
    data class NavigateToMovieDetails(val movieId: Int) : MyListDetailsUiEvent

    data class ShowSnackBar(val message:String): MyListDetailsUiEvent
}