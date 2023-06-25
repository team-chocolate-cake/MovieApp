package com.chocolatecake.viewmodel.episode_details

import com.chocolatecake.bases.BaseInteractionListener

interface EpisodeDetailsListener : BaseInteractionListener {
    fun clickToBack()
    fun clickToRate(episodeId: Int)



}
