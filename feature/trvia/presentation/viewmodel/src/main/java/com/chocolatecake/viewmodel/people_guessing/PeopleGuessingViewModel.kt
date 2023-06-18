package com.chocolatecake.viewmodel.people_guessing

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpPeopleUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentPeopleQuestion
import com.chocolatecake.usecase.game.questions.UpdatePeopleQuestionCountUseCase
import com.chocolatecake.viewmodel.common.AnswerListener
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleGuessingViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentPeopleQuestion: GetCurrentPeopleQuestion,
    private val updatePeopleQuestionCountUseCase: UpdatePeopleQuestionCountUseCase,
    private val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpPeopleUseCase: LevelUpPeopleUseCase,
) : BaseViewModel<GameUiState, GameUIEvent>(GameUiState()), AnswerListener {

    init {
        getData()
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onSuccessUser,
            ::onError
        )
        tryToExecute(
            getCurrentPeopleQuestion::invoke,
            ::onSuccessQuestion,
            ::onError
        )
    }

    private fun onSuccessQuestion(questionEntity: QuestionEntity) {
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

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isError = true) }
        sendEvent(GameUIEvent.ShowSnackbar(throwable.message.toString()))
    }

    private var timerJob: Job? = null
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

    private fun onUserLose() {
        viewModelScope.launch {
            sendEvent(GameUIEvent.NavigateToLoserScreen)
        }
    }

    override fun onClickAnswer(position: Int) {
        val heartCount = _state.value.heartCount
        val correctAnswer = _state.value.correctAnswerPosition
        if (correctAnswer != position) {
            if (heartCount - 1 == 0) {
                onUserLose()
            } else {
                _state.update { it.copy(heartCount = heartCount - 1) }
                updateCurrentQuestion()
            }
            return
        }
        if (_state.value.isLastQuestion) {
            onUserWins()
            return
        }
        updateToNextQuestion()
    }

    private fun onUserWins() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                levelUpPeopleUseCase()
                sendEvent(GameUIEvent.NavigateToWinnerScreen(GameType.PEOPLE))
            }.onFailure(::onError)
        }
    }

    private fun updateCurrentQuestion() {
        getData()
    }

    private fun updateToNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                updatePeopleQuestionCountUseCase(_state.value.questionCount)
                _state.update { it.copy(points = it.points + 100) }
                updateUserPointsUseCase(_state.value.points)
                getData()
            }.onFailure(::onError)
        }
    }
}