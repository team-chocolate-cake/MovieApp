package com.chocolatecake.viewmodel.showmore

import kotlin.math.roundToInt

data class ShowMoreUi(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val year: String,
    val genreEntities: String,
    val rate: Double,
){
    fun formattedRate(): Double = (rate * 10.0).roundToInt() / 10.0
}
