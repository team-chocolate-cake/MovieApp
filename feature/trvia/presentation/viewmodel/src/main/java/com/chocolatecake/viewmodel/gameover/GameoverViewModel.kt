package com.chocolatecake.viewmodel.gameover

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameoverViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    savedStateHandle: SavedStateHandle
):
BaseViewModel<GameoverUIState, GameoverUIEvent>(GameoverUIState()), GameoverListener{

    private val gameType: GameType = savedStateHandle.get<GameType>("gameType") ?: GameType.PEOPLE

    init {
        tryToExecute(getCurrentUserUseCase::invoke, ::onSuccessUser, ::onError)
    }

    private fun onSuccessUser(userEntity: UserEntity) {
        val level = when (gameType) {
            GameType.PEOPLE -> userEntity.peopleGameLevel
            GameType.MOVIE -> userEntity.moviesGameLevel
            GameType.TV_SHOW -> userEntity.tvShowGameLevel
            GameType.MEMORIZE -> userEntity.memorizeGameLevel
        }
        _state.update { it.copy(
            level = level,
            points = userEntity.totalPoints
        ) }
    }

    private fun onError(throwable: Throwable) {
        throwable.message
    }

    override fun onClickTryAgain(){
        sendEvent(GameoverUIEvent.NavigateToAnotherQuestion(gameType))
    }

    override fun onClickReturn(){
        sendEvent(GameoverUIEvent.NavigateToGameTypeScreen)
    }
}