package com.chocolatecake.movieapp.ui.home

sealed class HomeItem (val priority: Int){

    data class Slider(val list:List<Slider>):HomeItem(0)

    data class PopularPeople(val list:List<PopularPeople>):HomeItem(1)

    data class NowPlaying(val list:List<NowPlaying>):HomeItem(2)

    data class Trending(val list:List<NowPlaying>):HomeItem(3)

    data class TopRated(val list:List<NowPlaying>):HomeItem(4)

    data class PopularMovies(val list:List<NowPlaying>):HomeItem(5)

    data class RecommendedMovies(val list:List<NowPlaying>):HomeItem(6)

}
