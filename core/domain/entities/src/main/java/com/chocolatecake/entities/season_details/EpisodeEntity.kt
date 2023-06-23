package com.chocolatecake.entities.season_details

data class EpisodeEntity(
    val id: Int,
    val imageUrl: String,
    val title: String,
    val overview: String,
    val timeEpisode: Int,
    val rate: Double,
    val episodeNumber: Int
)
