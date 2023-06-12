package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.TrendingMoviesUiState

class TrendingAdapter(
    list: List<TrendingMoviesUiState>,
    listener: HomeListener
) : BaseAdapter<TrendingMoviesUiState>(list, listener) {
    override val layoutID = R.layout.home_item_trending
}