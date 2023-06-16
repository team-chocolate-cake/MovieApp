package com.chocolatecake.ui.movieDetails.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.movieDetails.CastUiState
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsListener

class PeopleAdapter(
    itemsPopular: List<CastUiState>,
    listener: MovieDetailsListener
) : BaseAdapter<CastUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.item_moviedetails_people

}