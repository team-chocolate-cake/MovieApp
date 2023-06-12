package com.chocolatecake.movieapp.ui.home

import com.chocolatecake.movieapp.ui.base.BaseInteractionListener

interface HomeListener : BaseInteractionListener {
    fun onClickUpComing(itemId: Int)
    fun onClickNowPlaying(itemId: Int)
    fun onClickTrending(itemId: Int)
    fun onClickPopularMovies(itemId: Int)
    fun onClickTopRated(itemId: Int)
    fun onClickPopularPeople(itemId: Int)
}