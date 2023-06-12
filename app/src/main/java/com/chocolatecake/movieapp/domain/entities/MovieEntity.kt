package com.chocolatecake.movieapp.domain.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity>,
    val rate: Double
)