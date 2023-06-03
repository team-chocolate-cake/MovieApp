package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class PopularAdapter(itemsPopular:List<PopularMovieEntity?>,listener: HomeListener):
    BaseAdapter<PopularMovieEntity?>(itemsPopular,listener) {
    override val layoutID: Int
        get() = TODO("Not yet implemented")

}





