package com.chocolatecake.viewmodel.profile.tv_shows

sealed interface TVShowsInteraction {
    data class NavigateToTVShowDetails(val itemId: Int) : TVShowsInteraction
    object ShowOnTheAirTVShowsResult : TVShowsInteraction
    object ShowAiringTodayTVShowsResult : TVShowsInteraction
    object ShowTopRatedTVShowsResult : TVShowsInteraction
    object ShowPopularTVShowsResult : TVShowsInteraction

}