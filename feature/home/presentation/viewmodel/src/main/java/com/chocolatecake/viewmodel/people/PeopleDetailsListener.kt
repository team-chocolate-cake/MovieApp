package com.chocolatecake.viewmodel.people

import com.chocolatecake.bases.BaseInteractionListener
import com.chocolatecake.viewmodel.common.listener.MediaListener

interface PeopleDetailsListener : MediaListener {
    fun onClickMovies(itemId: Int)
    fun onClickTvShows(itemId: Int)
    fun backNavigate()

}