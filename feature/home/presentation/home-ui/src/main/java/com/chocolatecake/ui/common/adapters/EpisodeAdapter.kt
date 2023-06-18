package com.chocolatecake.ui.common.adapters

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.EpisodeListener
import com.chocolatecake.viewmodel.common.model.EpisodeHorizontalUIState

class EpisodeAdapter(
    list: List<EpisodeHorizontalUIState>,
    listener: EpisodeListener
) : BaseAdapter<EpisodeHorizontalUIState>(list, listener) {
    override val layoutID = R.layout.item_episode_horizontal
}