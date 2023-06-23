package com.chocolatecake.entities.movieDetails


data class MovieDetailsEntity(
    val backdropPath: String = "",
    val credits: CreditsEntity,
    val genres: List<String> = emptyList(),
    val id: Int = 0,
    val overview: String= "",
    val recommendations: RecommendationsEntity,
    val title: String= "",
    val video: Boolean = false,
    val videos: VideosEntity,
    val voteAverage: Double = 0.0,
    val reviewEntity:ReviewResponseEntity
)