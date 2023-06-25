package com.chocolatecake.entities.my_rated

import com.chocolatecake.entities.GenreEntity


data class MyRatedMovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<GenreEntity>,
    val myRate: Double,
    val year: String = ""
)
