package com.chocolatecake.viewmodel.my_rated

import com.chocolatecake.viewmodel.tv_shows.TVShowsInteraction

sealed interface MyRatedEvents{
    object OnBackPressed:MyRatedEvents

    object ShowMyRatedMoviesPressed:MyRatedEvents

    object ShowMyRatedTvShowPressed:MyRatedEvents

    data class NavigateToTVShowDetails(val tvId: Int) : MyRatedEvents

    data class NavigateToMovieDetails(val movieId: Int) : MyRatedEvents
}