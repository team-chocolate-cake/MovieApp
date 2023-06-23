package com.chocolatecake.entities.myList

data class ListMovieEntity(
    val mediaId: Int,
    val listId: Int,
    val mediaType: String,
    val nameList: String,
    val posterPath: String,
)