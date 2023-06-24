package com.chocolatecake.viewmodel.gameover

import com.chocolatecake.viewmodel.common.model.GameType

sealed interface GameoverUIEvent {
    data class NavigateToAnotherQuestion(val gameType: GameType) : GameoverUIEvent
    object NavigateToGameTypeScreen : GameoverUIEvent
}