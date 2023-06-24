package com.chocolatecake.entities.my_rated


data class MyRatedMovieEntity(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val genreEntities: List<Int>,
    val myRate: Double,
    val year: String = ""
)
