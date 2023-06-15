package com.chocolatecake.ui.search

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.MovieListener
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState

class MediaAdapter(
    private val list: List<MovieHorizontalUIState>,
    private val listener: MovieListener
) : BaseAdapter<MovieHorizontalUIState>(list,listener) {
    override val layoutID: Int = R.layout.item_movie_horizontal
}