package com.chocolatecake.entities.movieDetails


data class MovieDetailsEntity(
    val backdropPath: String?,
    val credits: CreditsEntity?,
    val genres: List<String>?,
    val id: Int?,
    val overview: String?,
    val recommendations: RecommendationsEntity?,
    val title: String?,
    val video: Boolean,
    val videos: VideosEntity?,
    val voteAverage: Double?,
    val reviewEntities:List<ReviewEntity>?
)