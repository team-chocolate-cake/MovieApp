package com.chocolatecake.movieapp.ui.home

import com.chocolatecake.movieapp.ui.home.ui_state.NowPlayingUiState
import com.chocolatecake.movieapp.ui.home.ui_state.PopularMoviesUiState
import com.chocolatecake.movieapp.ui.home.ui_state.PopularPeopleUiState
import com.chocolatecake.movieapp.ui.home.ui_state.RecommendedUiState
import com.chocolatecake.movieapp.ui.home.ui_state.TopRatedUiState
import com.chocolatecake.movieapp.ui.home.ui_state.TrendingMoviesUiState
import com.chocolatecake.movieapp.ui.home.ui_state.UpComingMoviesUiState

sealed class HomeItem(val type: HomeItemType) {

    data class Slider(val list: List<UpComingMoviesUiState>) : HomeItem(HomeItemType.SLIDER)

    data class NowPlaying(val list: List<NowPlayingUiState>) :
        HomeItem(HomeItemType.NOW_PLAYING)

    data class Trending(val list: List<TrendingMoviesUiState>) : HomeItem(HomeItemType.TRENDING)

    data class TopRated(val list: List<TopRatedUiState>) : HomeItem(HomeItemType.TOP_RATED)

    data class PopularPeople(val list: List<PopularPeopleUiState>) :
        HomeItem(HomeItemType.POPULAR_PEOPLE)

    data class PopularMovies(val list: List<PopularMoviesUiState>) :
        HomeItem(HomeItemType.POPULAR_MOVIES)

    data class RecommendedMovies(val list: List<RecommendedUiState>) :
        HomeItem(HomeItemType.RECOMMENDED)

    enum class HomeItemType { SLIDER, NOW_PLAYING, TRENDING, TOP_RATED, POPULAR_PEOPLE, POPULAR_MOVIES, RECOMMENDED }

}
