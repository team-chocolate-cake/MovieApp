package com.chocolatecake.viewmodel.tv_shows

import com.chocolatecake.bases.BaseInteractionListener

interface TVShowsListener : BaseInteractionListener {
    fun onClickTVShows(tvId: Int)
    fun scrollToTopScreen()
    fun showOnTheAiringTVShowsResult()
    fun showAiringTodayTVShowsResult()
    fun showTopRatedTVShowsResult()
    fun showPopularTVShowsResult()
}