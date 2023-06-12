package com.chocolatecake.movieapp.ui.home.adapter

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.home.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.home.ui_state.PopularPeopleUiState

class PopularPeopleAdapter(
    itemsPopularPeople: List<PopularPeopleUiState>,
    listener: HomeListener
) :
    BaseAdapter<PopularPeopleUiState>(itemsPopularPeople, listener) {
    override val layoutID = R.layout.home_item_popular_people

}