package com.chocolatecake.viewmodel.myListDetails


data class MyListDetailsUiState(
    val movies: List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList()
)
