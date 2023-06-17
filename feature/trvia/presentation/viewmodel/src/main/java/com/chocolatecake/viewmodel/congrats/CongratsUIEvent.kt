package com.chocolatecake.viewmodel.congrats

import com.chocolatecake.viewmodel.common.model.GameType

sealed interface CongratsUIEvent {
    data class NavigateToNextLevel(val gameType: GameType): CongratsUIEvent
    object NavigateToGameTypeScreen : CongratsUIEvent
}