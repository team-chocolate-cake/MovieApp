package com.chocolatecake.entities

data class MovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity> = emptyList(),
    val rate: Double
)