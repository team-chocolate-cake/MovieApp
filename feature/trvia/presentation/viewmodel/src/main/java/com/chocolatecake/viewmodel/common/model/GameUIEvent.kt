package com.chocolatecake.viewmodel.common.model

sealed interface GameUIEvent {
    data class NavigateToWinnerScreen(val gameType: GameType): GameUIEvent
    object NavigateToLoserScreen: GameUIEvent
    object ShowTimeOut : GameUIEvent
    data class ShowSnackbar(val message: String): GameUIEvent
    data class ShowBuyHeartDialog(val numberOfPoints:Int):GameUIEvent
}