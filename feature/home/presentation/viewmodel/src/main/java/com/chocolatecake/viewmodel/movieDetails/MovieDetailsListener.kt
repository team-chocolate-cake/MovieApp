package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.bases.BaseInteractionListener

interface MovieDetailsListener : BaseInteractionListener {
    fun onClickPlayTrailer(keys: List<String>)
    fun onClickRate(id: Int)
    fun onClickBackButton()
    fun onClickSaveButton(id: Int)

    fun onClickShowMore(movieId:Int)
}