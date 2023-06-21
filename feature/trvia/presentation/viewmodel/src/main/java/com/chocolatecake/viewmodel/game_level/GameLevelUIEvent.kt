package com.chocolatecake.viewmodel.game_level

sealed interface GameLevelUIEvent {
    object NavigateToPeopleGame : GameLevelUIEvent
    object NavigateToMovieGame : GameLevelUIEvent
    object NavigateToTvGame : GameLevelUIEvent
    object NavigateToMemorizeBoard : GameLevelUIEvent
    object ShowYouMustPassPreviousLevelFirst : GameLevelUIEvent
    data class ShowSnckbar(val message: String) : GameLevelUIEvent
}