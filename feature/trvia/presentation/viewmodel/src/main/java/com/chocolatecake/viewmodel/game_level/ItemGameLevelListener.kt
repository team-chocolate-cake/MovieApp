package com.chocolatecake.viewmodel.game_level

import com.chocolatecake.bases.BaseInteractionListener
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState

interface ItemGameLevelListener : BaseInteractionListener {
    fun onClickItem(itemGameLevelUIState: ItemGameLevelUIState)
}