package com.chocolatecake.ui.people.adapter
import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.people.PeopleDetailsListener
import com.chocolatecake.viewmodel.people.PersonDetailsUiState


class  PeopleDetailsRecyclerAdapter(items: List<PersonDetailsUiState.PeopleMediaUiState>, listener: PeopleDetailsListener):
    BaseAdapter<PersonDetailsUiState.PeopleMediaUiState>(items, listener) {

    override val layoutID = R.layout.item_people_media
}