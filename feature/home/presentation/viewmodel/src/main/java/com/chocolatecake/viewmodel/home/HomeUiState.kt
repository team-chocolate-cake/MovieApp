package com.chocolatecake.viewmodel.home

data class HomeUiState(
    val upComingMovies: List<UpComingMoviesUiState> = emptyList(),
    val nowPlayingMovies: List<NowPlayingUiState> = emptyList(),
    val trendingMovies: List<TrendingMoviesUiState> = emptyList(),
    val popularPeople: List<PopularPeopleUiState> = emptyList(),
    val popularMovies: List<PopularMoviesUiState> = emptyList(),
    val topRated: List<TopRatedUiState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false,
)

data class UpComingMoviesUiState(
    val id: Int,
    val imageUrl: String,
)

data class NowPlayingUiState(
    val id: Int,
    val imageUrl: String,
)

data class TrendingMoviesUiState(
    val id: Int,
    val imageUrl: String,
    val rate: Double
)

data class PopularPeopleUiState(
    val id: Int,
    val profilePath: String,
    val name: String
)

data class PopularMoviesUiState(
    val id: Int,
    val imageUrl: String,
    val rate: Double
)

data class TopRatedUiState(
    val id: Int,
    val imageUrl: String,
    val rate: Double
)