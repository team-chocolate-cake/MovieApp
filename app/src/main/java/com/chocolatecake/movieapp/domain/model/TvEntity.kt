package com.chocolatecake.movieapp.domain.model

data class TvEntity(
    val id: Int = 0,
    val genreIds: List<Int> = emptyList(),
    val posterPath: String = "",
    val name: String = "",
    val voteAverage: Double = 0.0,
)
