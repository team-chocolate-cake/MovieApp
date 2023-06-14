package com.chocolatecake.ui.common.adapters

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.common.listener.SeasonListener
import com.chocolatecake.viewmodel.common.model.SeasonHorizontalUIState

class SeasonAdapter(
    list: List<SeasonHorizontalUIState>,
    listener: SeasonListener
) : BaseAdapter<SeasonHorizontalUIState>(list, listener) {
    override val layoutID = R.layout.item_season_horizontal
}