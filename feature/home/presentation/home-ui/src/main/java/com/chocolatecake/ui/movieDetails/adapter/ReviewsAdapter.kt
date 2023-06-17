package com.chocolatecake.ui.movieDetails.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsListener
import com.chocolatecake.viewmodel.movieDetails.ReviewUiState

class ReviewsAdapter(
    itemsPopular: List<ReviewUiState>,
    listener: MovieDetailsListener
) : BaseAdapter<ReviewUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.item_review

}