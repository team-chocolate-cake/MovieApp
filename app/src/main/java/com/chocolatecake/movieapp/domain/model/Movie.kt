package com.chocolatecake.movieapp.domain.model

data class Movie(
    val id: Int = 0,
    val genreIds: List<Int> = emptyList(),
    val posterPath: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
)