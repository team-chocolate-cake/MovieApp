package com.chocolatecake.entities

data class RatingEpisodeDetailsStatusEntity(
    val statusCode: Int = 0,
    val statusMessage: String = "",
    val success: Boolean = false
)