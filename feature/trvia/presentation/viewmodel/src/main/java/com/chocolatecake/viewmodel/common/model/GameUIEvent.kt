package com.chocolatecake.viewmodel.common.model

sealed interface GameUIEvent {
    data class UpdateQuestion(val questionNumber: Int): GameUIEvent
    data class NavigateToWinnerScreen(val points: Int, val level: Int): GameUIEvent
    object NavigateToWelcomeGameScreen: GameUIEvent
    object ShowTimeOut : GameUIEvent
}