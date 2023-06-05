package com.chocolatecake.movieapp.home

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import javax.inject.Inject


class HomeViewModel @Inject constructor(private val repository: MovieRepository) :
    BaseViewModel() , HomeListener{
    override fun getData() {
        TODO("Not yet implemented")
    }

    override fun onClickNowPlaying(itemId: Int) {

    }

    override fun onClickTrending(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularMovies(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickTopRated(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickRecommended(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickUpComing(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularPeople(itemId: Int) {
        TODO("Not yet implemented")
    }

}