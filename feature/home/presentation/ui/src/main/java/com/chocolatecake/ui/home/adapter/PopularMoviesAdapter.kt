package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.PopularMoviesUiState

class PopularMoviesAdapter(
    itemsPopular: List<PopularMoviesUiState>,
    listener: HomeListener
) : BaseAdapter<PopularMoviesUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.home_item_popular_movies

}





