package com.chocolatecake.viewmodel.people

import com.chocolatecake.bases.BaseInteractionListener

interface PeopleDetailsListener : BaseInteractionListener {
    fun onClickMovies(itemId: Int)
    fun onClickTvShows(itemId: Int)
    fun onClickShowMore(itemId: Int)
}