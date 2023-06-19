package com.chocolatecake.viewmodel.home

sealed interface HomeUiEvent{
    data class PopularMovieEvent(val itemId: Int) : HomeUiEvent
    data class PopularPeopleEvent(val itemId: Int) : HomeUiEvent
    data class NowPlayingMovieEvent(val itemId: Int) : HomeUiEvent
    data class RecommendedMovieEvent(val itemId: Int) : HomeUiEvent
    data class TopRatedMovieEvent(val itemId: Int) : HomeUiEvent
    data class TrendingMovieEvent(val itemId: Int) : HomeUiEvent
    data class UpComingMovieEvent(val itemId: Int) : HomeUiEvent

    object ClickShowMore : HomeUiEvent
}