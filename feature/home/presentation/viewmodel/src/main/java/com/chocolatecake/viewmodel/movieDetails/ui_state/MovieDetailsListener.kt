package com.chocolatecake.viewmodel.movieDetails.ui_state

import com.chocolatecake.bases.BaseInteractionListener

interface MovieDetailsListener : BaseInteractionListener {
    fun onClickPeople(itemId: Int)
    fun onClickRecommendedMovie(itemId: Int)
}