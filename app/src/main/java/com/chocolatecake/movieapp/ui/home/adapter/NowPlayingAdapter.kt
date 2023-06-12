package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.home.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.NowPlayingUiState


class NowPlayingAdapter(
    list: List<NowPlayingUiState>, listener: HomeListener
) :
    BaseAdapter<NowPlayingUiState>(list, listener) {
    override val layoutID = R.layout.home_item_now_playing
}