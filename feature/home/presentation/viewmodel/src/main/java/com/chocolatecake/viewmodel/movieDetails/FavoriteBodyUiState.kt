package com.chocolatecake.viewmodel.movieDetails



data class FavoriteBodyUiState(
    val favorite: Boolean?,
    val mediaId: Int?,
    val mediaType: String,
)