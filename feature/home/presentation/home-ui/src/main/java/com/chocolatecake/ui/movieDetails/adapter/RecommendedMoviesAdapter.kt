package com.chocolatecake.ui.movieDetails.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsListener
import com.chocolatecake.viewmodel.movieDetails.RecommendedMoviesUiState

class RecommendedMoviesAdapter(
    itemsPopular: List<RecommendedMoviesUiState>,
    listener: MovieDetailsListener
) : BaseAdapter<RecommendedMoviesUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.item_movie

}