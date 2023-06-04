package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter


class NowPlayingAdapter(list: List<NowPlayingMovieEntity>, listener :HomeListener): BaseAdapter<NowPlayingMovieEntity>(list,listener) {
    override val layoutID: Int
        get() = R.layout.home_item_now_playing
}