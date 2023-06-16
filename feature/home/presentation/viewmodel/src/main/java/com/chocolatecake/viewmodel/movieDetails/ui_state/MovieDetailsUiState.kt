package com.chocolatecake.viewmodel.movieDetails.ui_state

import com.chocolatecake.viewmodel.movieDetails.MovieDetailsItem


data class MovieDetailsUiState(
    val movieUiState: MovieDetailsItem = MovieDetailsItem.Upper(null),
    val recommendedUiState: MovieDetailsItem = MovieDetailsItem.Recommended(emptyList()),
    val castUiState: MovieDetailsItem = MovieDetailsItem.People(emptyList()),
    val reviewUiState: MovieDetailsItem = MovieDetailsItem.Reviews(emptyList()),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)

data class UpperUiState(
    val id: Int?,
    val backdropPath: String?,
    val genres: List<String>?,
    val title: String?,
    val overview: String?,
    val voteAverage: Float?,
    val videos: List<String>?,
)

data class RecommendedMoviesUiState(
    val id: Int?,
    val voteAverage: Double?,
    val backdropPath: String?,
)
data class CastUiState(
    val id: Int?,
    val name: String?,
    val profilePath: String?
)
data class ReviewUiState(
    val name:String?,
    val avatar_path:String?,
    val content:String?,
    val created_at:String?,
)