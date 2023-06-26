package com.chocolatecake.viewmodel.congrats

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class CongratsViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<CongratsUIState, CongratsUIEvent>(CongratsUIState()) {

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
        val numOfQuestions = when (gameType) {
            GameType.PEOPLE -> userEntity.numPeopleQuestionsPassed
            GameType.MOVIE -> userEntity.numMoviesQuestionsPassed
            GameType.TV_SHOW -> userEntity.numTvShowQuestionsPassed
            else -> -1
        }
        _state.update {
            it.copy(
                level = level,
                userName = userEntity.username,
                points = userEntity.totalPoints,
                isCompletedLevel = numOfQuestions == 15
            )
        }
    }

    private fun onError(throwable: Throwable) {

    }

    fun onClickNextLevel() {
        sendEvent(CongratsUIEvent.NavigateToNextLevel(gameType))
    }

    fun onClickReturn() {
        sendEvent(CongratsUIEvent.NavigateToGameTypeScreen)
    }

}