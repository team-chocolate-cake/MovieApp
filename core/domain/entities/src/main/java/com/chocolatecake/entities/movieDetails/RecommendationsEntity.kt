package com.chocolatecake.entities.movieDetails



data class RecommendationsEntity(
    val page: Int,
    val recommendedMovies: List<RecommendedMovieEntity>,
    val totalPages: Int,
    val totalResults: Int
)