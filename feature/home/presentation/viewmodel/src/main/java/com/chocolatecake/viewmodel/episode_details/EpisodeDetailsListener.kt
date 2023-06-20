package com.chocolatecake.viewmodel.episode_details

import com.chocolatecake.bases.BaseInteractionListener

interface EpisodeDetailsListener : BaseInteractionListener {
    fun clickToRate()
    fun playEpisode()
    fun applyRating()
}
