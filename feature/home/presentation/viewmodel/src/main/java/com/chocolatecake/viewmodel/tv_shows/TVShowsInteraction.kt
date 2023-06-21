package com.chocolatecake.viewmodel.tv_shows

sealed interface TVShowsInteraction {
    data class NavigateToTVShowDetails(val tvId: Int) : TVShowsInteraction
    object ShowOnTheAirTVShowsResult : TVShowsInteraction
    object ShowAiringTodayTVShowsResult : TVShowsInteraction
    object ShowTopRatedTVShowsResult : TVShowsInteraction
    object ShowPopularTVShowsResult : TVShowsInteraction
    data class ShowSnackBar(val messages: String) : TVShowsInteraction
}