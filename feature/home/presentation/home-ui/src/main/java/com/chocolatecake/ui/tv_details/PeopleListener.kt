package com.chocolatecake.ui.tv_details

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.PeopleUIState

class PeopleAdapter(
    list: List<PeopleUIState>,
    listener: PeopleListener
) : BaseAdapter<PeopleUIState>(list, listener) {
    override val layoutID = R.layout.item_people
}