package com.chocolatecake.viewmodel.watch_history.state_managment

import java.util.Date

data class MovieUiState(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val year: Int,
    val rating: Double,
    val dateWatched: Date?
){
    val rate: Double
        get() = (rating * 10.0).toInt() / 10.0
}