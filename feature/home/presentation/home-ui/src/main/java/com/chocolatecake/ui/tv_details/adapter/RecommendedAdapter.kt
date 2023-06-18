package com.chocolatecake.ui.tv_details.adapter

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.home.RecommendedUiState
import com.chocolatecake.viewmodel.tv_details.RecommendedListener

class RecommendedAdapter(
    itemRecommended: List<RecommendedUiState>,
    listener: RecommendedListener
) : BaseAdapter<RecommendedUiState>(itemRecommended, listener) {
    override val layoutID = R.layout.item_media_vertical
}