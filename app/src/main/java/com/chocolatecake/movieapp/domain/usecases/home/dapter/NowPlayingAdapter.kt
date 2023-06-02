package com.chocolatecake.movieapp.domain.usecases.home.dapter

import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.ui.base.BaseAdapter


class NowPlayingAdapter(list: List<NowPlayingMovieEntity>,): BaseAdapter<NowPlayingMovieEntity>(list,) {
    override val layoutID: Int
        get() =
}