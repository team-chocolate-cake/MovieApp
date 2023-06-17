package com.chocolatecake.viewmodel.game_type

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class GameTypeViewModel: ViewModel(),GameTypeListener {

    private val _event = MutableSharedFlow<GameTypeUIEvent>()
    val event = _event.asSharedFlow()

    private fun sendEvent(event: GameTypeUIEvent) {
        viewModelScope.launch { _event.emit(event) }
    }

    override fun onClickGamePeople() {
        sendEvent(GameTypeUIEvent.NavigateToPeopleGameBoard)
    }

    override fun onClickGameTvShows() {
        sendEvent(GameTypeUIEvent.NavigateToTvGameBoard)
    }

    override fun onClickGameMovie() {
        sendEvent(GameTypeUIEvent.NavigateToMovieGameBoard)
    }

    override fun onClickGameMemorize() {
        sendEvent(GameTypeUIEvent.NavigateToMemorizeBoard)
    }

    override fun onClickCommingSoon() {
        sendEvent(GameTypeUIEvent.ShowSnackbar)

    }
}