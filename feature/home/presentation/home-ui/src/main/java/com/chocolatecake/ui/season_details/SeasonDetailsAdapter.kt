package com.chocolatecake.ui.season_details

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.EpisodeListener
import com.chocolatecake.viewmodel.common.model.EpisodeHorizontalUIState

class SeasonDetailsAdapter (
    private val list: List<EpisodeHorizontalUIState>,
    private val listener: EpisodeListener
):
    BaseAdapter<EpisodeHorizontalUIState>(list, listener) {
    override val layoutID: Int = R.layout.item_episode_horizontal
}