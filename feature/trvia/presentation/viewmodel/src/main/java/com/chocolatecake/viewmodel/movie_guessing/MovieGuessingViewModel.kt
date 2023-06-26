package com.chocolatecake.viewmodel.movie_guessing

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpMoviesUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentMovieQuestion
import com.chocolatecake.usecase.game.questions.UpdateMoviesQuestionCountUseCase
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
class MovieGuessingViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentMovieQuestion: GetCurrentMovieQuestion,
    private val updateMovieQuestionCountUseCase: UpdateMoviesQuestionCountUseCase,
    private val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpMovieUseCase: LevelUpMoviesUseCase,
) : BaseViewModel<GameUiState, GameUIEvent>(GameUiState(gameType = GameType.MOVIE)),
    AnswerListener {

    init {
        getData()
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true, userAnswer = null) }
        tryToExecute(
            getCurrentUserUseCase::invoke,
            ::onSuccessUser,
            ::onError
        )
        tryToExecute(
            getCurrentMovieQuestion::invoke,
            ::onSuccessQuestion,
            ::onError
        )
    }

    private fun onSuccessQuestion(questionEntity: QuestionEntity) {
        _state.update {
            it.copy(
                question = questionEntity.question,
                answers = questionEntity.choices.mapIndexed {
                        indexAnswer, answer ->
                    GameUiState.AnswerUiState(
                        indexAnswer,
                        answer,
                        isCorrect = indexAnswer == questionEntity.correctAnswerPosition
                    )
                },
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
                level = user.moviesGameLevel,
                points = user.totalPoints,
                questionCount = user.numMoviesQuestionsPassed + 1,
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
            delay(300)
            sendEvent(GameUIEvent.NavigateToLoserScreen)
        }
    }

    private fun onUserLose() {
        sendEvent(GameUIEvent.NavigateToLoserScreen)
    }


    override fun onClickAnswer(position: Int) {
        viewModelScope.launch {
            _state.update { it.copy(userAnswer = position) }
            delay(500)
            val heartCount = _state.value.heartCount
            val correctAnswer = _state.value.correctAnswerPosition
            if (correctAnswer != position) {
                if (heartCount - 1 == 0) {
                    onUserLose()
                } else {
                    _state.update { it.copy(heartCount = heartCount - 1) }
                    getData()
                }
                return@launch
            }
            if (_state.value.isLastQuestion) {
                onUserWins()
                return@launch
            }
            updateToNextQuestion()
        }
    }

    private fun onUserWins() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                levelUpMovieUseCase()
                sendEvent(GameUIEvent.NavigateToWinnerScreen(GameType.MOVIE))
            }.onFailure(::onError)
        }
    }

    private fun updateToNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                updateMovieQuestionCountUseCase(_state.value.questionCount)
                _state.update { it.copy(points = it.points + (it.level * 10)) }
                updateUserPointsUseCase(_state.value.points)
                getData()
            }.onFailure(::onError)
        }
    }

}