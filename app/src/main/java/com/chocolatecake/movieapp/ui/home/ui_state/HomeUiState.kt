package com.chocolatecake.movieapp.ui.home.ui_state

data class HomeUiState(
    val upComingMovies:List<UpComingMoviesUiState> = emptyList(),
    val nowPlayingMovies:List<NowPlayingUiState> = emptyList(),
    val trendingMovies:List<TrendingMoviesUiState> = emptyList() ,
    val popularPeople:List<PopularPeopleUiState> = emptyList(),
    val popularMovies:List<PopularMoviesUiState> = emptyList(),
    val topRated:List<TopRatedUiState> = emptyList(),
    val recommended:List<RecommendedUiState> = emptyList(),
    val onErrors:List<Error> = emptyList(),
    val loading:Boolean=false,
)
