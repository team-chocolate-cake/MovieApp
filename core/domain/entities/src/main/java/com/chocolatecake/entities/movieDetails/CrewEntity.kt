package com.chocolatecake.entities.movieDetails



data class CrewEntity(
    val adult: Boolean = false,
    val creditId: String = "",
    val department: String= "",
    val gender: Int= 0,
    val id: Int = 0,
    val job: String= "",
    val knownForDepartment: String= "",
    val name: String= "",
    val originalName: String= "",
    val popularity: Double = 0.0,
    val profilePath: String= ""
)