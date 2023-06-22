package com.chocolatecake.viewmodel.home

import com.chocolatecake.bases.BaseInteractionListener

interface HomeListener : BaseInteractionListener {
    fun onClickUpComing(itemId: Int)
    fun onClickNowPlaying(itemId: Int)
    fun onClickTrending(itemId: Int)
    fun onClickPopularMovies(itemId: Int)
    fun onClickTopRated(itemId: Int)
    fun onClickPopularPeople(itemId: Int)

    fun onClickTrendingShowMore()
    fun onClickTopRatedShowMore()
    fun onClickPopularMoviesShowMore()
}