package com.chocolatecake.ui.tv_details.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState

class RecommendedAdapter(
    itemRecommended: List<MediaVerticalUIState>,
    listener: MediaListener
) : BaseAdapter<MediaVerticalUIState>(itemRecommended, listener) {
    override val layoutID = R.layout.item_media_vertical
}