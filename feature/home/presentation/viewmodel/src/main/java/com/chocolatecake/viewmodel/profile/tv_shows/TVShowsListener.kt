package com.chocolatecake.viewmodel.profile.tv_shows

import com.chocolatecake.bases.BaseInteractionListener

interface TVShowsListener : BaseInteractionListener {
    fun onClickTVShows(itemId: Int)
    fun showOnTheAiringTVShowsResult()
    fun showAiringTodayTVShowsResult()
    fun showTopRatedTVShowsResult()
    fun showPopularTVShowsResult()
}