package com.chocolatecake.ui.people.adapter
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsListener
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiState
import com.chocolatecake.viewmodel.people.PeopleDetailsListener


class  PeopleDetailsRecyclerAdapter(items: List<MediaVerticalUIState>, listener: PeopleDetailsListener):
    BaseAdapter<MediaVerticalUIState>(items, listener) {

    override val layoutID = R.layout.item_media_vertical
}