package com.chocolatecake.ui.tv_details.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.tv_details.listener.RecommendedListener

class RecommendedAdapter(
    itemRecommended: List<MediaVerticalUIState>,
    listener: RecommendedListener
) : BaseAdapter<MediaVerticalUIState>(itemRecommended, listener) {
    override val layoutID = R.layout.item_media_vertical
}