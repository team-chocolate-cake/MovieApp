package com.chocolatecake.viewmodel.home

import com.chocolatecake.bases.BaseInteractionListener

interface HomeListener : BaseInteractionListener {
    fun onClickMovieDetails(itemId: Int)

    fun onClickShowMore()
}