package com.chocolatecake.viewmodel.movie_guessing

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpMoviesUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentMovieQuestion
import com.chocolatecake.usecase.game.questions.UpdateMoviesQuestionCountUseCase
import com.chocolatecake.viewmodel.common.BaseTriviaQuestionGameViewModel
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MovieGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentMovieQuestion: GetCurrentMovieQuestion,
    private val updateMovieQuestionCountUseCase: UpdateMoviesQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpMovieUseCase: LevelUpMoviesUseCase,
) : BaseTriviaQuestionGameViewModel(state = GameUiState()) {
    override val gameType = GameType.MOVIE
    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentMovieQuestion::invoke

    override fun onSuccessUser(user: UserEntity) {
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

    override suspend fun levelUpUseCase() {
        levelUpMovieUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updateMovieQuestionCountUseCase(questionCount)
    }

}