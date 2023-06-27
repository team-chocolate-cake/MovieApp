package com.chocolatecake.viewmodel.memorize_game

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.BoardEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.GetCurrentBoardUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpMemorizeUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MemorizeGameViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentBoardUseCase: GetCurrentBoardUseCase,
    private val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpMemorizeUseCase: LevelUpMemorizeUseCase
) : BaseViewModel<MemorizeGameUIState, MemorizeGameUIEvent>(MemorizeGameUIState()),
    MemorizeListener {

    init {
        getData()
    }

    private fun getData() {
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onSuccessUser,
            ::onError
        )
        tryToExecute(
            getCurrentBoardUseCase::invoke,
            ::onSuccessBoard,
            ::onError
        )
    }

    private fun onSuccessBoard(boardEntity: BoardEntity) {
        _state.update {
            val board = boardEntity.itemsEntity.toUIState()
            it.copy(
                boardSize = boardEntity.itemsEntity.size,
                board = board,
                initialBoard = board,
                isLoading = false,
                isError = false
            )
        }
        initTimer()
    }

    private fun onSuccessUser(user: UserEntity) {
        _state.update {
            val maxTime = when (user.memorizeGameLevel) {
                1 -> 30
                2 -> 60
                else -> 90
            }
            it.copy(
                level = user.memorizeGameLevel,
                points = user.totalPoints,
                isError = false,
                isLoading = false,
                initialCounter = maxTime,
                countDownTimer = maxTime
            )
        }
    }


    private var timerJob: Job? = null
    private fun initTimer() {
        _state.update { it.copy(countDownTimer = it.initialCounter) }
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                if (_state.value.countDownTimer == 0) {
                    onUserLose()
                    timerJob = null
                    cancel()
                }
                delay(1000)
                _state.update { it.copy(countDownTimer = it.countDownTimer - 1) }
            }
        }
    }

    private fun onUserLose() {
        sendEvent(MemorizeGameUIEvent.NavigateToLoserScreen)
    }

    override fun onItemClick(itemGameImageUiState: ItemGameImageUiState) {
        if (itemGameImageUiState.isSelected){
            return
        }
        viewModelScope.launch {
            toggleGameItem(itemGameImageUiState)
            delay(500)
            saveUserPosition(itemGameImageUiState.position)
            checkIfWinner()
        }
    }

    private fun checkIfWinner() {
        if (state.value.isWinner) {
            onUserWins()
        }
    }

    private fun toggleGameItem(itemGameImageUiState: ItemGameImageUiState) {
        val modifyItem = itemGameImageUiState.copy(isSelected = !itemGameImageUiState.isSelected)
        val modifyBoard = (_state.value.board - itemGameImageUiState).toMutableList()
        modifyBoard.add(modifyItem.position, modifyItem)
        _state.update { it.copy(board = modifyBoard) }
    }

    private fun saveUserPosition(position: Int) {
        if (state.value.firstUserPosition == null) {
            saveFirstPosition(position)
        } else {
            saveSecondPosition(position)
        }
    }

    private fun saveFirstPosition(position: Int) {
        _state.update { it.copy(firstUserPosition = position) }
    }

    private fun saveSecondPosition(position: Int) {
        val firstPosition = _state.value.firstUserPosition!!
        with(state.value) {
            if (board[firstPosition].imageUrl == board[position].imageUrl) {
                _state.update {
                    it.copy(
                        selectedUserPositions = (selectedUserPositions + firstPosition + position).distinct(),
                        secondUserPosition = null,
                        firstUserPosition = null
                    )
                }
            } else {
                toggleGameItem(board[firstPosition]);  toggleGameItem(board[position])
                _state.update {
                    it.copy(
                        firstUserPosition = null,
                        secondUserPosition = null,
                    )
                }
            }
        }
    }

    private fun onUserWins() {
        viewModelScope.launch {
            delay(500)
            _state.update { it.copy(points = it.points + (it.level * 20)) }
            updateUserPointsUseCase(_state.value.points)
            levelUpMemorizeUseCase()
            sendEvent(MemorizeGameUIEvent.NavigateToWinnerScreen(GameType.MEMORIZE))
        }
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isError = true) }
        sendEvent(MemorizeGameUIEvent.ShowSnackbar(throwable.message.toString()))
    }
}


private fun List<BoardEntity.ItemBoardEntity>.toUIState(): List<ItemGameImageUiState> {
    return this.map {
        ItemGameImageUiState(
            imageUrl = it.imageUrl,
            position = it.position,
        )
    }
}
