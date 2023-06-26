package com.chocolatecake.viewmodel.game_level

import com.chocolatecake.viewmodel.game_type.GameTypeUIEvent

sealed interface GameLevelUIEvent {
    object NavigateToPeopleGame : GameLevelUIEvent
    object NavigateToMovieGame : GameLevelUIEvent
    object NavigateToTvGame : GameLevelUIEvent
    object NavigateToMemorizeBoard : GameLevelUIEvent
    object ShowYouMustPassPreviousLevelFirst : GameLevelUIEvent
    object ResetLevels : GameLevelUIEvent
    object BackNavigate : GameLevelUIEvent
    data class ShowSnckbar(val message: String) : GameLevelUIEvent
}