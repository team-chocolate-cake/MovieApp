package com.chocolatecake.viewmodel.people_guessing

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentPeopleQuestion
import com.chocolatecake.usecase.game.questions.UpdatePeopleQuestionCountUseCase
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.GameUIEvent
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleGuessingViewModel @Inject constructor(
    private val getCurrentPeopleQuestion: GetCurrentPeopleQuestion,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val updatePeopleQuestionCountUseCase: UpdatePeopleQuestionCountUseCase,
    private val updateUserPointsUseCase: UpdateUserPointsUseCase,
) :
    BaseViewModel<GameUiState, GameUIEvent>(GameUiState()), AnswerListener {
    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            getCurrentUserUseCase().let { user ->
                _state.update {
                    it.copy(
                        level = user.memorizeGameLevel,
                        points = user.totalPoints,
                        questionCount = user.numPeopleQuestionsPassed + 1,
                    )
                }
            }
            getCurrentPeopleQuestion().let { questionEntity ->
                _state.update {
                    it.copy(
                        question = questionEntity.question,
                        answers = questionEntity.choices,
                        correctAnswerPosition = questionEntity.correctAnswerPosition,
                        imageUrl = questionEntity.imageUrl,
                    )
                }
            }
            initTimer()
        }
    }

    private fun initTimer() {
        viewModelScope.launch {
            while (true) {
                if (_state.value.countDownTimer == 0) {
                    onUserLose()
                    break
                }
                delay(1000)
                _state.update { it.copy(countDownTimer = it.countDownTimer - 1) }
            }
        }
    }

    private fun onUserLose() {
        sendEvent(GameUIEvent.NavigateToLoserScreen)
    }

    override fun onClickAnswer(position: Int) {
        val heartCount = _state.value.heartCount
        val correctAnswer = _state.value.correctAnswerPosition
        if (correctAnswer != position) {
            if (heartCount - 1 == 0) {
                onUserLose()
            } else {
                _state.update {
                    it.copy(
                        heartCount = heartCount - 1
                    )
                }
                updateCurrentQuestion()
            }
            return
        }
        if (_state.value.isLastQuestion) {
            sendEvent(GameUIEvent.NavigateToWinnerScreen(GameType.PEOPLE))
            return
        }
        updateToNextQuestion()
    }

    private fun updateCurrentQuestion() {
        getData()
    }

    private fun updateToNextQuestion() {
        viewModelScope.launch {
            updatePeopleQuestionCountUseCase(_state.value.questionCount)
            _state.update { it.copy(
                points = it.points + 100
            ) }
            updateUserPointsUseCase(_state.value.points)
            getData()
        }
    }
}