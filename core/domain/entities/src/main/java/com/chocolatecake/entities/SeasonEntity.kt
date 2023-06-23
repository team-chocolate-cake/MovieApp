package com.chocolatecake.entities

data class SeasonEntity(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val description: String,
    val year: String,
    val countEpisode: Int,
    val seasonNumber: Int = 0,
)
