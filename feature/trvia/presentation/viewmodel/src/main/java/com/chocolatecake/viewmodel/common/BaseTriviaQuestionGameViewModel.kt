package com.chocolatecake.viewmodel.common

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseTriviaQuestionGameViewModel(
    state: GameUiState,
) : BaseViewModel<GameUiState, GameUIEvent>(state), AnswerListener {
    abstract val getCurrentUserUseCase: GetCurrentUserUseCase
    abstract val updateUserPointsUseCase: UpdateUserPointsUseCase
    abstract val getQuestion: suspend () -> QuestionEntity
    private var timerJob: Job? = null
    abstract val gameType: GameType


    protected fun getData() {
        _state.update { it.copy(isLoading = true) }
        getCurrentUser()
        getUserQuestion()
    }

    private fun getCurrentUser() {
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onSuccessUser,
            ::onError
        )
    }

    private fun getUserQuestion() {
        tryToExecute(
            call = getQuestion::invoke,
            ::onSuccessQuestion,
            ::onError
        )
    }

    abstract fun onSuccessUser(user: UserEntity)

    open fun onSuccessQuestion(questionEntity: QuestionEntity) {
        _state.update {
            it.copy(
                question = questionEntity.question,
                answers = questionEntity.choices,
                correctAnswerPosition = questionEntity.correctAnswerPosition,
                imageUrl = questionEntity.imageUrl,
                isError = false,
                isLoading = false
            )
        }
        initTimer()
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isError = true) }
        sendEvent(GameUIEvent.ShowSnackbar(throwable.message.toString()))
    }

    private fun initTimer() {
        _state.update { it.copy(countDownTimer = 30) }
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (true) {
                if (_state.value.countDownTimer == 0) {
                    onTimeout()
                    timerJob = null
                    cancel()
                }
                delay(1000)
                _state.update { it.copy(countDownTimer = it.countDownTimer - 1) }
            }
        }
    }

    private fun onTimeout() {
        viewModelScope.launch {
            sendEvent(GameUIEvent.ShowTimeOut)
            delay(1000)
            sendEvent(GameUIEvent.NavigateToLoserScreen)
        }
    }

    override fun onClickAnswer(position: Int) {
        val heartCount = _state.value.heartCount
        val correctAnswer = _state.value.correctAnswerPosition

        if (correctAnswer != position) {
            if (heartCount - 1 == 0) {
                onEmptyHearts()
            } else {
                _state.update { it.copy(heartCount = heartCount - 1) }
                getData()
            }
            return
        }
        if (_state.value.isLastQuestion) {
            viewModelScope.launch {
                onUserWins()
            }
            return
        }
        updateToNextQuestion()
    }

    private fun onEmptyHearts() {
        state.value.apply {
            when {
                (level == 1 && points < HeartsDifficultyPoints.EASY.points) ||
                        (level == 2 && points < HeartsDifficultyPoints.MEDIUM.points) ||
                        (level == 3 && points < HeartsDifficultyPoints.HARD.points) -> {
                    sendEvent(GameUIEvent.NavigateToLoserScreen)
                }

                else -> {
                    val numberOfPoints = when (level) {
                        1 -> HeartsDifficultyPoints.EASY.points
                        2 -> HeartsDifficultyPoints.MEDIUM.points
                        3 -> HeartsDifficultyPoints.HARD.points
                        else -> 0
                    }
                    sendEvent(GameUIEvent.ShowBuyHeartDialog(numberOfPoints))
                    timerJob?.cancel()
                }
            }
        }
    }

    private fun onUserWins() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                updateUserPointsUseCase(_state.value.points)
                updateQuestionCountUseCase(_state.value.questionCount)
                levelUpUseCase()
                sendEvent(GameUIEvent.NavigateToWinnerScreen(gameType))
            }.onFailure(::onError)
        }
    }

    abstract suspend fun levelUpUseCase()

    private fun updateToNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                updateQuestionCountUseCase(_state.value.questionCount)
                _state.update { it.copy(points = it.points + (it.level * 10)) }
                updateUserPointsUseCase(_state.value.points)
                getData()
            }.onFailure(::onError)
        }
    }

    abstract suspend fun updateQuestionCountUseCase(questionCount: Int)

    fun buyHearts(numberOfPoints: Int) {
        _state.update { it.copy(points = it.points - numberOfPoints, heartCount = 3) }
        viewModelScope.launch {
            updateUserPointsUseCase(_state.value.points)
        }
        initTimer()
    }

    companion object {
        enum class HeartsDifficultyPoints(val points: Int) {
            EASY(30),
            MEDIUM(60),
            HARD(150)
        }
    }
}