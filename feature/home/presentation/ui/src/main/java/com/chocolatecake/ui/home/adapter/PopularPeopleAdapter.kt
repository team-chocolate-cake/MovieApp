package com.chocolatecake.ui.home.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.R
import com.chocolatecake.viewmodel.home.HomeListener
import com.chocolatecake.viewmodel.home.PopularPeopleUiState

class PopularPeopleAdapter(
    itemsPopularPeople: List<PopularPeopleUiState>,
    listener: HomeListener
) : BaseAdapter<PopularPeopleUiState>(itemsPopularPeople, listener) {
    override val layoutID = R.layout.home_item_popular_people

}