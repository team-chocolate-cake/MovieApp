package com.chocolatecake.viewmodel.game_level

sealed interface GameLevelUIEvent {
    object NavigateToPeopleGame : GameLevelUIEvent
    object NavigateToMovieGame : GameLevelUIEvent
    object NavigateToTvGame : GameLevelUIEvent
    object NavigateToMemorizeBoard : GameLevelUIEvent
    object ShowYouMustPassPreviousLevelFirst : GameLevelUIEvent
    object ResetLevels : GameLevelUIEvent
    object BackNavigate : GameLevelUIEvent
    data class ShowSnckbar(val message: String) : GameLevelUIEvent
    object PlaySound : GameLevelUIEvent
}