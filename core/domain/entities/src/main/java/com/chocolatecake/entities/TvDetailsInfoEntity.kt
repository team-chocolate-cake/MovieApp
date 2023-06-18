package com.chocolatecake.entities

data class TvDetailsInfoEntity(
    val backdropImageUrl: String,
    val name: String,
    val rating: Float,
    val description: String,
    val genres: List<GenreEntity>
)