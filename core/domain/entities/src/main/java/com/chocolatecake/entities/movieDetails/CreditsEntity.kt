package com.chocolatecake.entities.movieDetails



data class CreditsEntity(
    val cast: List<CastEntity?>,
    val crew: List<CrewEntity?>
)