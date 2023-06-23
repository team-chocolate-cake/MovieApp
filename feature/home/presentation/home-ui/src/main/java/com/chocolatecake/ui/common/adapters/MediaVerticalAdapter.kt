package com.chocolatecake.ui.common.adapters

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.people.PeopleDetailsViewModel

class MediaVerticalAdapter(
    list: List<MediaVerticalUIState>,
    listener: PeopleDetailsViewModel
) : BaseAdapter<MediaVerticalUIState>(list, listener) {
    override val layoutID = R.layout.item_media_vertical
}