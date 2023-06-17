package com.chocolatecake.entities

data class TVShowsEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity>,
    val rate: Double
)
