package com.chocolatecake.entities.movieDetails



data class CastEntity(
    val adult: Boolean = false,
    val castId: Int = 0,
    val character: String = "",
    val creditId: String= "",
    val gender: Int = 0,
    val id: Int = 0,
    val knownForDepartment: String= "",
    val name: String= "",
    val order: Int = 0,
    val originalName: String= "",
    val popularity: Double = 0.0,
    val profilePath: String= ""
)