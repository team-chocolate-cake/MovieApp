package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.UpComingMoviesUiState

class UpComingAdapter(
      upComingList: List<UpComingMoviesUiState>, listener: HomeListener
) : BaseAdapter<UpComingMoviesUiState>(upComingList,listener) {
    override val layoutID = R.layout.home_item_image_slider
}
