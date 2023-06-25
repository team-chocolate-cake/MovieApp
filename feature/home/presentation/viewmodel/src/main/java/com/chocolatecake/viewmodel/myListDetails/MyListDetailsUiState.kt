package com.chocolatecake.viewmodel.myListDetails


data class MyListDetailsUiState(
    val movies: List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList(),
    val deletedMovie: MovieUiState? = null,
    val swipePosition: Int? = null,
    val snackBarUndoPressed: Boolean? = null,
)
