package com.chocolatecake.viewmodel.common.model

sealed interface GameUIEvent {
    data class UpdateQuestion(val questionNumber: Int): GameUIEvent
    data class NavigateToWinnerScreen(val gameType: GameType): GameUIEvent
    object NavigateToLoserScreen: GameUIEvent
    object ShowTimeOut : GameUIEvent
}