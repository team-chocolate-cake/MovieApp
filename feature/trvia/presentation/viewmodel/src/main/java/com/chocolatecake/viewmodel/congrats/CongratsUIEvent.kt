package com.chocolatecake.viewmodel.congrats

sealed interface CongratsUIEvent {
    data class ToggleItemGame(val itemPosition: Int) : CongratsUIEvent
    data class UpdateLevel(val level: Int): CongratsUIEvent
    object NavigateToWinnerScreen : CongratsUIEvent
    object NavigateToLoserScreen : CongratsUIEvent
    object NavigateToWelcomeGameScreen : CongratsUIEvent
    object NavigateToNextLevel: CongratsUIEvent
    object NavigateToHome: CongratsUIEvent

}