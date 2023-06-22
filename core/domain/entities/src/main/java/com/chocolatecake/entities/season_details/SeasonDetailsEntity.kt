package com.chocolatecake.entities.season_details

data class SeasonDetailsEntity(
    val id: Int,
    val name : String,
    val overview : String,
    val episodes : List<EpisodeEntity>
)
