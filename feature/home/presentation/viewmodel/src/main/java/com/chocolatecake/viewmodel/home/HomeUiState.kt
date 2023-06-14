package com.chocolatecake.viewmodel.home

data class HomeUiState(
    val upComingMovies: HomeItem = HomeItem.Slider(emptyList()),
    val nowPlayingMovies: HomeItem = HomeItem.NowPlaying(emptyList()),
    val trendingMovies: HomeItem = HomeItem.Trending(emptyList()),
    val popularPeople: HomeItem = HomeItem.PopularPeople(emptyList()),
    val popularMovies: HomeItem = HomeItem.PopularMovies(emptyList()),
    val topRated: HomeItem = HomeItem.TopRated(emptyList()),
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

data class RecommendedUiState(
    val id: Int,
    val imageUrl: String,
    val rate: Double
)

