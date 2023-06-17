package com.chocolatecake.viewmodel.game_type

sealed interface GameTypeUIEvent{
    object NavigateToPeopleGame : GameTypeUIEvent
    object NavigateToMovieGame : GameTypeUIEvent
    object NavigateToTvGame : GameTypeUIEvent
    object NavigateToMemorizeBoard : GameTypeUIEvent
    object ShowSnackbar : GameTypeUIEvent

}