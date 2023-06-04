package com.chocolatecake.movieapp.ui.home.ui_state

data class HomeUiState(
    val upComingMovies:List<UpComingMoviesUiState>,
    val nowPlayingMovies:List<NowPlayingUiState>,
    val trendingMovies:List<TrendingMoviesUiState> ,
    val popularPeople:List<PopularPeopleUiState>,
    val popularMovies:List<PopularMoviesUiState>,
    val topRated:List<TopRatedUiState>,
    val recommended:List<RecommendedUiState>,
    val onErrors:List<Error> = emptyList(),
    val loading:Boolean=false,
)
