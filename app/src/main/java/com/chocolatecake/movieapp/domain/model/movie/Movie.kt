package com.chocolatecake.movieapp.domain.model.movie

import com.chocolatecake.movieapp.domain.model.Genre

data class Movie(
    val id: Int,
    val genres: List<Genre>,
    val imageUrl: String,
    val title: String,
    val voteAverage: Double
)