package com.chocolatecake.ui.search

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.PeopleUIState

class PeopleAdapter(
    private val list: List<PeopleUIState>,
    private val listener: PeopleListener
) : BaseAdapter<PeopleUIState>(list,listener) {
    override val layoutID: Int = R.layout.item_people
}