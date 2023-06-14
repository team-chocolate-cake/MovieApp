package com.chocolatecake.ui.common.adapters

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.MovieListener
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState

class MovieHorizontalAdapter(
    list: List<MovieHorizontalUIState>,
    listener: MovieListener
) : BaseAdapter<MovieHorizontalUIState>(list, listener) {
    override val layoutID = R.layout.item_movie_horizontal
}