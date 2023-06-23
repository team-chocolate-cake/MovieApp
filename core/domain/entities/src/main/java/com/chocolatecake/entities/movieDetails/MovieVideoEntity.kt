package com.chocolatecake.entities.movieDetails



data class MovieVideoEntity(
    val id: String="",
    val iso31661: String="",
    val iso6391: String="",
    val key: String = "",
    val name: String="",
    val official: Boolean = false,
    val publishedAt: String="",
    val site: String="",
    val size: Int= 0,
    val type: String=""
)