package com.chocolatecake.viewmodel.game_type

import com.chocolatecake.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameTypeViewModel @Inject constructor() : BaseViewModel<Unit, GameTypeUIEvent>(Unit) {

    fun toggleSound() {
        sendEvent(GameTypeUIEvent.PlaySound)
    }
    fun onClickGamePeople() {
        sendEvent(GameTypeUIEvent.NavigateToPeopleGame)
    }

    fun onClickGameTvShows() {
        sendEvent(GameTypeUIEvent.NavigateToTvGame)
    }

    fun onClickGameMovie() {
        sendEvent(GameTypeUIEvent.NavigateToMovieGame)
    }

    fun onClickGameMemorize() {
        sendEvent(GameTypeUIEvent.NavigateToMemorizeBoard)
    }
    fun onClickBackIcon() {
        sendEvent(GameTypeUIEvent.BackNavigate)
    }
    fun onClickCommingSoon() {
        sendEvent(GameTypeUIEvent.ShowSnackbar)
    }
}