package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class PopularMoviesAdapter(itemsPopular:List<PopularMovieEntity?>, listener: HomeListener):
    BaseAdapter<PopularMovieEntity?>(itemsPopular,listener) {
    override val layoutID: Int
        get() = R.layout.home_item_popular_movies

}





