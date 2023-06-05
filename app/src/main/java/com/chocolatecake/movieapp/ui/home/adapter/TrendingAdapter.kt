package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class TrendingAdapter(
    list: List<TrendingMoviesEntity>,
    layout: Int,
    listner: HomeListener
) :
    BaseAdapter<TrendingMoviesEntity>(list, listner) {
    override val layoutID = layout

}