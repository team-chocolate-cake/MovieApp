package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.base.BaseInteractionListener

class TopRatedAdapter(
    itemsTopRated: List<TopRatedMovieEntity?>,
    layout: Int,
    listener: HomeListener
) :
    BaseAdapter<TopRatedMovieEntity?>(itemsTopRated, listener) {
    override val layoutID = layout

}