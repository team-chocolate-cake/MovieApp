package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.TrendingMoviesUiState

class TrendingAdapter(
    list: List<TrendingMoviesUiState>,
    layout: Int,
    listner: HomeListener
) :
    BaseAdapter<TrendingMoviesUiState>(list, listner) {
    override val layoutID = layout

}