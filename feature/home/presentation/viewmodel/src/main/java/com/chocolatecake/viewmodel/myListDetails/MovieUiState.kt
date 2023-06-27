package com.chocolatecake.viewmodel.myListDetails

import kotlin.math.roundToInt

data class MovieUiState(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val genres: String,
    val year: String,
    val rate: Double,
){
    fun formattedRate(): Double = (rate * 10.0).roundToInt() / 10.0
}