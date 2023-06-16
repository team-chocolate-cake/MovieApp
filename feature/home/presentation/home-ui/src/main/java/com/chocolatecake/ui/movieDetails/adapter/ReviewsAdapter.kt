package com.chocolatecake.ui.movieDetails.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.PopularMoviesUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.CastUiState
import com.chocolatecake.viewmodel.movieDetails.ui_state.MovieDetailsListener
import com.chocolatecake.viewmodel.movieDetails.ui_state.ReviewUiState

class ReviewsAdapter(
    itemsPopular: List<ReviewUiState>,
    listener: MovieDetailsListener
) : BaseAdapter<ReviewUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.item_review

}