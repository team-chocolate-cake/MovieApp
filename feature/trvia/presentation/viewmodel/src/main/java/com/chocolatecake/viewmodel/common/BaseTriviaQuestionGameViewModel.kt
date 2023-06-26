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
    abstract val gameType: GameType
    abstract val getCurrentUserUseCase: GetCurrentUserUseCase
    abstract val updateUserPointsUseCase: UpdateUserPointsUseCase
    abstract val getQuestion: suspend () -> QuestionEntity
    private var timerJob: Job? = null

    abstract suspend fun levelUpUseCase()
    abstract fun onSuccessUser(user: UserEntity)
    abstract suspend fun updateQuestionCountUseCase(questionCount: Int)

    protected fun getData() {
        _state.update { it.copy(isLoading = true) }
        getCurrentUser()
        getUserQuestion()
    }

    //region user data
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
    //endregion

    //region timer
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

    //endregion

    //region handle question
    override fun onClickAnswer(questionChosenPosition: Int) {
        val isCorrectAnswer = _state.value.correctAnswerPosition == questionChosenPosition
        when {
            (!isCorrectAnswer && state.value.heartCount == 1) -> handleIncorrectAnswerWithNoHearts()
            (!isCorrectAnswer) -> handleIncorrectAnswerWithRemainingHearts()
            else -> onQuestionCompletion()
        }
    }

    private fun handleIncorrectAnswerWithNoHearts() {
        state.value.apply {
            when {
                points < requiredPointsForHearts(level) ->
                    sendEvent(GameUIEvent.NavigateToLoserScreen)

                else -> {
                    sendEvent(GameUIEvent.ShowBuyHeartDialog(requiredPointsForHearts(level)))
                    timerJob?.cancel()
                }
            }
        }
    }

    private fun requiredPointsForHearts(level: Int): Int {
        return when (level) {
            1 -> HEARTS_POINTS_FOR_EASY_LEVEL
            2 -> HEARTS_POINTS_FOR_MEDIUM_LEVEL
            3 -> HEARTS_POINTS_FOR_HARD_LEVEL
            else -> 0
        }
    }

    private fun handleIncorrectAnswerWithRemainingHearts() {
        _state.update { it.copy(heartCount = state.value.heartCount - 1) }
        getData()
    }

    private fun onQuestionCompletion() {
        if (_state.value.isLastQuestion) {
            viewModelScope.launch { onUserWins() }
        } else {
            updateToNextQuestion()
        }
    }

    private fun onUserWins() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                updateUserPointsUseCase(_state.value.points)
                updateQuestionCountUseCase(_state.value.questionCount)
                levelUpUseCase()
                sendEvent(GameUIEvent.NavigateToWinnerScreen(gameType))
            }.onFailure(::onError)
        }
    }

    private fun updateToNextQuestion() {
        viewModelScope.launch(Dispatchers.IO) {
            runCatching {
                updateQuestionCountUseCase(_state.value.questionCount)
                _state.update { it.copy(points = it.points + (it.level * 10)) }
                updateUserPointsUseCase(_state.value.points)
                getData()
            }.onFailure(::onError)
        }
    }
    //endregion

    fun buyHearts(numberOfPoints: Int) {
        _state.update { it.copy(points = it.points - numberOfPoints, heartCount = 3) }
        viewModelScope.launch {
            updateUserPointsUseCase(_state.value.points)
        }
        initTimer()
    }

    private fun onError(throwable: Throwable) {
        _state.update { it.copy(isError = true) }
        sendEvent(GameUIEvent.ShowSnackbar(throwable.message.toString()))
    }

    companion object {
        const val HEARTS_POINTS_FOR_EASY_LEVEL = 30
        const val HEARTS_POINTS_FOR_MEDIUM_LEVEL = 60
        const val HEARTS_POINTS_FOR_HARD_LEVEL = 150
    }
}