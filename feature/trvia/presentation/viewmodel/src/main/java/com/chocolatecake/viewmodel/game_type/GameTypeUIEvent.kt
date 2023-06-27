package com.chocolatecake.viewmodel.game_type

sealed interface GameTypeUIEvent{
    object NavigateToPeopleGame : GameTypeUIEvent
    object NavigateToMovieGame : GameTypeUIEvent
    object NavigateToTvGame : GameTypeUIEvent

    object NavigateToMemorizeBoard : GameTypeUIEvent
    object BackNavigate : GameTypeUIEvent
    object PlaySound : GameTypeUIEvent
    object ShowSnackbar : GameTypeUIEvent

}