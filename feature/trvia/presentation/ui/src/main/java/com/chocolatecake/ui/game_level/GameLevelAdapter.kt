package com.chocolatecake.ui.game_level

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.trivia.R
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState
import com.chocolatecake.viewmodel.game_level.ItemGameLevelListener

class GameLevelAdapter(
    items: List<ItemGameLevelUIState>,
    listener: ItemGameLevelListener
) : BaseAdapter<ItemGameLevelUIState>(items, listener) {
    override val layoutID: Int = R.layout.item_card_level
}