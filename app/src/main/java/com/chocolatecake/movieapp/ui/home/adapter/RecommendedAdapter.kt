package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class RecommendedAdapter (itemsTopRated:List<RecommendedMovieEntity?>, listener: HomeListener):
    BaseAdapter<RecommendedMovieEntity?>(itemsTopRated,listener) {
    override val layoutID: Int
        get() = TODO("Not yet implemented")

}