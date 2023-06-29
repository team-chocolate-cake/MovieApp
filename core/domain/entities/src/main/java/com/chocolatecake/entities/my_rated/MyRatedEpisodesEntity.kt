package com.chocolatecake.entities.my_rated

data class MyRatedEpisodesEntity(
    val id: Int,
    val name: String,
    val episodeNumber: Int,
    val seasonNumber: Int,
    val showId: Int,
    val rating: Int,
    val voteAverage: Int,
    val voteCount: Int
)