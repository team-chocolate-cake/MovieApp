package com.chocolatecake.viewmodel.movieDetails

import com.chocolatecake.bases.BaseInteractionListener

interface MovieDetailsListener : BaseInteractionListener {
    fun onClickPeople(itemId: Int)
    fun onClickRecommendedMovie(itemId: Int)
    fun onClickPlayTrailer(key: Int)
    fun onClickRate(id: Int)
    fun onClickBackButton()
    fun onClickSaveButton()
}