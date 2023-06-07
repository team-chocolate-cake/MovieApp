package com.chocolatecake.movieapp.domain.model

data class Movie(
    val id: Int = 0,
    val genres: List<Genre> = emptyList(),
    val posterPath: String = "",
    val title: String = "",
    val voteAverage: Double = 0.0,
)