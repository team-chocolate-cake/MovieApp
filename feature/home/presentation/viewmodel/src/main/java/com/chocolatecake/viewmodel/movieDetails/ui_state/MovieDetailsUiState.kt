package com.chocolatecake.viewmodel.movieDetails.ui_state

import com.chocolatecake.entities.movieDetails.MovieDetailsEntity

data class MovieDetailsUiState(
    val movieDetailsEntity: MovieDetailsEntity? = null,
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)