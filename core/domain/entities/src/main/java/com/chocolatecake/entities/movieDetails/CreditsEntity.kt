package com.chocolatecake.entities.movieDetails



data class CreditsEntity(
    val cast: List<CastEntity> = emptyList(),
    val crew: List<CrewEntity> =emptyList()
)