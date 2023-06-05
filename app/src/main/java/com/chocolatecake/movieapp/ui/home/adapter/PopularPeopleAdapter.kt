package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.PopularPeopleUiState

class PopularPeopleAdapter(
    itemsPopularPeople: List<PopularPeopleUiState?>,
    layout: Int,
    listener: HomeListener
) :
    BaseAdapter<PopularPeopleUiState?>(itemsPopularPeople, listener) {
    override val layoutID = layout

}