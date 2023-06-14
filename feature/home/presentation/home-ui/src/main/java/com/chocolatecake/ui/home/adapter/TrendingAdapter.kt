package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.TrendingMoviesUiState

class TrendingAdapter(
    list: List<TrendingMoviesUiState>,
    listener: HomeListener
) : BaseAdapter<TrendingMoviesUiState>(list, listener) {
    override val layoutID = R.layout.home_item_trending
}