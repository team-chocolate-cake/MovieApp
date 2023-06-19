package com.chocolatecake.viewmodel.game_level

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameLevelViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<GameLevelUIState, GameLevelUIEvent>(GameLevelUIState()), ItemGameLevelListener {
    private val gameType = savedStateHandle.get<GameType>("game_level_type") ?: GameType.PEOPLE

    init {
        getUserData()
    }

    private fun getUserData() {
        _state.update { it.copy(isLoading = true, gameType = gameType) }
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onUserSuccess,
            ::onError
        )
    }

    private fun onUserSuccess(userEntity: UserEntity) {
        _state.update {
            it.copy(
                gameLevel = getGameLevelFromUser(userEntity),
                isError = false,
                isLoading = false,
            )
        }
    }

    private fun getGameLevelFromUser(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return when (gameType) {
            GameType.PEOPLE -> getPeopleGameLevelUISate(userEntity)
            GameType.MOVIE -> TODO()
            GameType.TV_SHOW -> TODO()
            GameType.MEMORIZE -> TODO()
        }
    }

    private fun getPeopleGameLevelUISate(userEntity: UserEntity): List<ItemGameLevelUIState> {
        return userEntity.run {
            listOf(
                ItemGameLevelUIState(
                    level = 1,
                    questionsCount = if (peopleGameLevel > 1) 5 else numPeopleQuestionsPassed,
                    max = 5,
                    isOpenLevel = peopleGameLevel >= 1
                ),
                ItemGameLevelUIState(
                    level = 2,
                    questionsCount = if (peopleGameLevel > 2) 10 else numPeopleQuestionsPassed,
                    max = 10,
                    isOpenLevel = peopleGameLevel >= 2
                ),
                ItemGameLevelUIState(
                    level = 3,
                    questionsCount = if (peopleGameLevel > 3) 15 else numPeopleQuestionsPassed,
                    max = 15,
                    isOpenLevel = peopleGameLevel >= 3
                ),
            )
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isError = true) }
        sendEvent(GameLevelUIEvent.ShowSnckbar(throwable.message.toString()))
    }

    override fun onClickItem(itemGameLevelUIState: ItemGameLevelUIState) {
        if (itemGameLevelUIState.isOpenLevel && !itemGameLevelUIState.isPassed) {
            when (state.value.gameType) {
                GameType.PEOPLE -> sendEvent(GameLevelUIEvent.NavigateToPeopleGame)
                GameType.MOVIE -> sendEvent(GameLevelUIEvent.NavigateToMovieGame)
                GameType.TV_SHOW -> sendEvent(GameLevelUIEvent.NavigateToTvGame)
                GameType.MEMORIZE -> sendEvent(GameLevelUIEvent.NavigateToMemorizeBoard)
            }
        } else if (!itemGameLevelUIState.isPassed) {
            sendEvent(GameLevelUIEvent.ShowYouMustPassPreviousLevelFirst)
        }
    }
}