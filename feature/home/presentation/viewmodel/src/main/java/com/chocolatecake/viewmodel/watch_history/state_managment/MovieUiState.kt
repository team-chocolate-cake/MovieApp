package com.chocolatecake.viewmodel.watch_history.state_managment

data class MovieUiState(
    val id: Int?,
    val imageUrl: String,
    val title: String,
    val description: String,
    val rating: Double
)