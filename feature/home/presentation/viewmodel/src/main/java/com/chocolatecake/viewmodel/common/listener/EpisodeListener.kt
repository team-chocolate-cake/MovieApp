package com.chocolatecake.viewmodel.common.listener

import com.chocolatecake.bases.BaseInteractionListener

interface EpisodeListener: BaseInteractionListener {
    fun onClickEpisode(id: Int)
}