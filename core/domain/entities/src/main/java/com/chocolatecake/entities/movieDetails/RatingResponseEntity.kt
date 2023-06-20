package com.chocolatecake.entities.movieDetails

data class RatingResponseEntity(
    val statusCode: Int = 0,
    val statusMessage: String = "",
    val success: Boolean = false
)