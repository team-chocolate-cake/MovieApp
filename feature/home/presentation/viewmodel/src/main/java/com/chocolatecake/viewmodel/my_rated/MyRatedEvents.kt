package com.chocolatecake.viewmodel.my_rated


sealed interface MyRatedEvents{
    object OnBackPressed:MyRatedEvents

    object ShowMyRatedMoviesPressed:MyRatedEvents

    object ShowMyRatedTvShowPressed:MyRatedEvents

    data class NavigateToTVShowDetails(val tvId: Int) : MyRatedEvents

    data class NavigateToMovieDetails(val movieId: Int) : MyRatedEvents
}