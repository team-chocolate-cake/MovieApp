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


    protected fun getData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onSuccessUser,
            ::onError
        )
        tryToExecute(
            call = getQuestion::invoke,
            ::onSuccessQuestion,
            ::onError
        )
    }

    private fun onSuccessUser(user: UserEntity) {
        _state.update {
            it.copy(
                level = user.peopleGameLevel,
                points = user.totalPoints,
                questionCount = user.numPeopleQuestionsPassed + 1,
                isError = false,
                isLoading = false
            )
        }
    }

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
            onUserWins()
            return
        }
        updateToNextQuestion()
    }

    private fun onEmptyHearts() {
        state.value.apply {
            when {
                (level == 1 && points < 30) ||
                        (level == 2 && points < 60) ||
                        (level == 3 && points < 150) -> {
                    sendEvent(GameUIEvent.NavigateToLoserScreen)
                }

                else -> {
                    val numberOfPoints = when (level) {
                        1 -> 30
                        2 -> 60
                        3 -> 150
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
                levelUpUseCase()
                sendEvent(GameUIEvent.NavigateToWinnerScreen(GameType.MOVIE))
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
        _state.update { it.copy(points = it.points - numberOfPoints) }
        _state.update { it.copy(heartCount = 3) }
        initTimer()
    }
}