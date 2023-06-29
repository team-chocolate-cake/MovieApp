package com.chocolatecake.viewmodel.my_list_details

data class MovieUiState(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val genres: String,
    val year: String,
    val rating: Double,
    val mediaType: String,
){
    val rate: Double
        get() = (rating * 10.0).toInt() / 10.0
}