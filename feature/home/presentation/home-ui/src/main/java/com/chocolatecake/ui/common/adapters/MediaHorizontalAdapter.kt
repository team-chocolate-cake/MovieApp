package com.chocolatecake.ui.common.adapters

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.model.MediaHorizontalUIState

class MediaHorizontalAdapter(
    list: List<MediaHorizontalUIState>,
    listener: MediaListener
) : BaseAdapter<MediaHorizontalUIState>(list, listener) {
    override val layoutID = R.layout.item_media_horizontal
}