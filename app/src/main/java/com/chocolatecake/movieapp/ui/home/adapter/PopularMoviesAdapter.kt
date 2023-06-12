package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.PopularMoviesUiState

class PopularMoviesAdapter(
    itemsPopular: List<PopularMoviesUiState>,
    listener: HomeListener
) :
    BaseAdapter<PopularMoviesUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.home_item_popular_movies

}





