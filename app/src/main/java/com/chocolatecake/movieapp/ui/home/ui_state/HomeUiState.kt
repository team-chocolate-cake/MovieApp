package com.chocolatecake.movieapp.ui.home.ui_state

data class HomeUiState(
    val upComingMovies:List<UpComingMoviesUiState>,
    val nowPlayingMovies:List<NowPlayingUiState>,
    val trendingMovies:List<TrendingMoviesUiState> ,
    val popularPeople:List<PopularPeopleUiState>
)
