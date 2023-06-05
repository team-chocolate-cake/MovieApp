package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.RecommendedUiState

class RecommendedAdapter(
    itemsRecommended: List<RecommendedUiState>,
    listener: HomeListener
) :
    BaseAdapter<RecommendedUiState>(itemsRecommended, listener) {
    override val layoutID = R.layout.home_item_recommended
}