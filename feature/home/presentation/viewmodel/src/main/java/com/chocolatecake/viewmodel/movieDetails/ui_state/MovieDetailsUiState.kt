package com.chocolatecake.viewmodel.movieDetails.ui_state



data class MovieDetailsUiState(
    val movieUiState: MovieUiState? = null,
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)

data class MovieUiState(
    val id: Int?,
    val backdropPath: String?,
    val genres: List<String>?,
    val recommendations: List<RecommendedMoviesUiState>?,
    val title: String?,
    val overview: String?,
    val voteAverage: Double?,
    val videos: List<String?>?,
    val cast: List<CastUiState>?,
    val reviews: List<ReviewUiState>?,
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