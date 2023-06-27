package com.chocolatecake.entities.my_rated

import com.chocolatecake.entities.GenreEntity


data class MyRatedTvShowEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity>,
    val rate: Double,
    val year: String = ""
)
