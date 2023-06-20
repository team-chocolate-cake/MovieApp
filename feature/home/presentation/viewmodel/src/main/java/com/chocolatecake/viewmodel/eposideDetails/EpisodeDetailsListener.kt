package com.chocolatecake.viewmodel.eposideDetails

import com.chocolatecake.bases.BaseInteractionListener

interface EpisodeDetailsListener : BaseInteractionListener {
    fun clickToRate()
    fun playEpisode()
    fun applyRating()
}
