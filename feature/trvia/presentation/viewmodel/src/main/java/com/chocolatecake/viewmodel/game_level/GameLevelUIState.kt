package com.chocolatecake.viewmodel.game_level

import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState

data class GameLevelUIState(
    val gameLevel: List<ItemGameLevelUIState> = emptyList(),
    val gameType: GameType = GameType.PEOPLE,
    val isLoading: Boolean = true,
    val isError: Boolean = false
)
