package com.chocolatecake.movieapp.ui.home

import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity

sealed class HomeItem (val type: HomeItemType){

    data class Slider(val list:List<UpcomingMovieEntity>):HomeItem(HomeItemType.SLIDER)

    data class NowPlaying(val list:List<NowPlayingMovieEntity>):HomeItem(HomeItemType.NOW_PLAYING)

    data class Trending(val list:List<TrendingMoviesEntity>):HomeItem(HomeItemType.TRENDING)

    data class TopRated(val list:List<TopRatedMovieEntity>):HomeItem(HomeItemType.TOP_RATED)

    data class PopularPeople(val list:List<PopularPeopleEntity>):HomeItem(HomeItemType.POPULAR_PEOPLE)

    data class PopularMovies(val list:List<PopularMovieEntity>):HomeItem(HomeItemType.POPULAR_MOVIES)

    data class RecommendedMovies(val list:List<RecommendedMovieEntity>):HomeItem(HomeItemType.RECOMMENDED)

    enum class HomeItemType { SLIDER, NOW_PLAYING, TRENDING, TOP_RATED,POPULAR_PEOPLE,POPULAR_MOVIES,RECOMMENDED }

}
