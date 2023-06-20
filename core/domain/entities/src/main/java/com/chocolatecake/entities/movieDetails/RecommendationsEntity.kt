package com.chocolatecake.entities.movieDetails



data class RecommendationsEntity(
    val page: Int = 0,
    val recommendedMovies: List<RecommendedMovieEntity> = emptyList(),
    val totalPages: Int = 0,
    val totalResults: Int=0
)