package com.chocolatecake.viewmodel.my_list_details


sealed interface MyListDetailsUiEvent {
    data class NavigateToMovieDetails(val movieId: Int) : MyListDetailsUiEvent

    data class ShowSnackBar(val message:String): MyListDetailsUiEvent

    object OnClickBack : MyListDetailsUiEvent
}