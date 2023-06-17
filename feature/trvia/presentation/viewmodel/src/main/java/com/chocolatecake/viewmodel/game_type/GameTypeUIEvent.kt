package com.chocolatecake.viewmodel.game_type

sealed interface GameTypeUIEvent{
    object NavigateToPeopleGameBoard : GameTypeUIEvent
    object NavigateToMovieGameBoard : GameTypeUIEvent
    object NavigateToTvGameBoard : GameTypeUIEvent
    object NavigateToMemorizeBoard : GameTypeUIEvent
    object ShowSnackbar : GameTypeUIEvent

}