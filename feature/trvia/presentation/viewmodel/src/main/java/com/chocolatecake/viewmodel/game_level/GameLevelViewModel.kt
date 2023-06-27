package com.chocolatecake.viewmodel.game_level

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.levelup.ResetLevelUpMemorizeUseCase
import com.chocolatecake.usecase.game.levelup.ResetLevelUpMoviesUseCase
import com.chocolatecake.usecase.game.levelup.ResetLevelUpPeopleUseCase
import com.chocolatecake.usecase.game.levelup.ResetLevelUpTvShowUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.ItemGameLevelUIState
import com.chocolatecake.viewmodel.game_level.utill.GameLevelGenerator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameLevelViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val gameLevelGenerator: GameLevelGenerator,
    private val resetLevelUpMoviesUseCase: ResetLevelUpMoviesUseCase,
    private val resetLevelUpMemorizeUseCase: ResetLevelUpMemorizeUseCase,
    private val resetLevelUpPeopleUseCase: ResetLevelUpPeopleUseCase,
    private val resetLevelUpTvShowUseCase: ResetLevelUpTvShowUseCase,
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
            GameType.PEOPLE -> gameLevelGenerator.getPeopleGameLevelUISate(userEntity)
            GameType.MOVIE -> gameLevelGenerator.getMovieGameLevelUISate(userEntity)
            GameType.TV_SHOW -> gameLevelGenerator.getTvGameLevelUISate(userEntity)
            GameType.MEMORIZE -> gameLevelGenerator.getMemorizeGameLevelUISate(userEntity)
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

    fun onClickRestart() {
        sendEvent(GameLevelUIEvent.ResetLevels)
    }

    fun resetGameLevelFromUser() {
        viewModelScope.launch {
            when (gameType) {
                GameType.PEOPLE -> resetLevelUpPeopleUseCase()
                GameType.MOVIE -> resetLevelUpMoviesUseCase()
                GameType.TV_SHOW -> resetLevelUpTvShowUseCase()
                GameType.MEMORIZE -> resetLevelUpMemorizeUseCase()
            }
            _state.update { currentState ->
                currentState.copy(gameLevel = currentState.gameLevel.map { level ->
                    level.copy(questionsCount = 0, isOpenLevel = level.level==1)

                })
            }
        }
    }
    fun onClickBackIcon() {
        sendEvent(GameLevelUIEvent.BackNavigate)
    }
    fun toggleSound() {
        sendEvent(GameLevelUIEvent.PlaySound)
    }
}