package com.chocolatecake.entities.movieDetails



data class MovieVideoEntity(
    val id: String?,
    val iso31661: String?,
    val iso6391: String?,
    val key: String = "",
    val name: String?,
    val official: Boolean,
    val publishedAt: String?,
    val site: String?,
    val size: Int?,
    val type: String?
)