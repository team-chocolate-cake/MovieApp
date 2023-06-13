package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.NowPlayingUiState


class NowPlayingAdapter(
    list: List<NowPlayingUiState>, listener: HomeListener
) : BaseAdapter<NowPlayingUiState>(list, listener) {
    override val layoutID = R.layout.home_item_now_playing
}