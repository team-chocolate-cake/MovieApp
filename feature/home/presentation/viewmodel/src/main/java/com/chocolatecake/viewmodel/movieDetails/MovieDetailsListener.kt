package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.bases.BaseInteractionListener

interface MovieDetailsListener : BaseInteractionListener {
    fun onClickPlayTrailer()
    fun onClickRate(id: Int)
    fun onClickBackButton()
    fun onClickShowMore(movieId:Int)
}