package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.RecommendedUiState

class RecommendedAdapter (itemsTopRated:List<RecommendedUiState>, layout:Int,listener: HomeListener):
    BaseAdapter<RecommendedUiState>(itemsTopRated,listener) {
    override val layoutID = layout

}