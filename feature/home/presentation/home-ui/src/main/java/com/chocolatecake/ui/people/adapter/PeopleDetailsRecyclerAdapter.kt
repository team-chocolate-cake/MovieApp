package com.chocolatecake.ui.people.adapter
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsListener
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiState
import com.chocolatecake.viewmodel.people.PeopleDetailsListener
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState


class  PeopleDetailsRecyclerAdapter(items: List<PeopleDetailsUiState.PeopleMediaUiState>, listener: PeopleDetailsListener):
    BaseAdapter<PeopleDetailsUiState.PeopleMediaUiState>(items, listener) {

    override val layoutID = R.layout.item_people_media
}