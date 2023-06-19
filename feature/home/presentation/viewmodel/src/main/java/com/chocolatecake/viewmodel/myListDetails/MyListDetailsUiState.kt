package com.chocolatecake.viewmodel.myListDetails

import com.chocolatecake.entities.GenreEntity

data class MyListDetailsUiState(
    val movies: List<MovieUiState> = emptyList(),
    val isLoading: Boolean = false,
    val errors: List<Error> = emptyList()
)
