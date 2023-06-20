package com.chocolatecake.viewmodel.myList

data class MyListUiState(
    val movieList: List<ListMovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList()
)
