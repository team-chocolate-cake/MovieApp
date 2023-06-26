package com.chocolatecake.viewmodel.myListDetails


data class MyListDetailsUiState(
    val movies: List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val deletedMovie: MovieUiState? = null,
    val swipePosition: Int? = null,
    val snackBarUndoPressed: Boolean? = null,
    val error: List<String>? = null,

){
    val isFailure: Boolean = error?.isNotEmpty() == true
}
