package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.TopRatedUiState

class TopRatedAdapter(
    itemsTopRated: List<TopRatedUiState>,
    listener: HomeListener
) :
    BaseAdapter<TopRatedUiState>(itemsTopRated, listener) {
    override val layoutID = R.layout.home_item_top_rated

}