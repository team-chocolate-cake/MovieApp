package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.TopRatedUiState

class TopRatedAdapter(
    itemsTopRated: List<TopRatedUiState>,
    listener: HomeListener
) : BaseAdapter<TopRatedUiState>(itemsTopRated, listener) {
    override val layoutID = R.layout.home_item_top_rated

}