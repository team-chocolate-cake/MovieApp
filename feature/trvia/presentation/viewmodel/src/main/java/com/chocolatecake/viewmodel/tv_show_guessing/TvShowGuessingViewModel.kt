package com.chocolatecake.viewmodel.tv_show_guessing

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.entities.UserEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpMoviesUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpTvShowUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentTvShowQuestion
import com.chocolatecake.usecase.game.questions.UpdateTvShowsQuestionCountUseCase
import com.chocolatecake.viewmodel.common.BaseTriviaQuestionGameViewModel
import com.chocolatecake.viewmodel.common.model.GameType
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvShowGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentTvShowQuestion: GetCurrentTvShowQuestion,
    private val updateTvShowsQuestionCountUseCase: UpdateTvShowsQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpTvShowUseCase: LevelUpTvShowUseCase,
):BaseTriviaQuestionGameViewModel(state = GameUiState()) {
    override val gameType: GameType = GameType.TV_SHOW

    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentTvShowQuestion::invoke

    override fun onSuccessUser(user: UserEntity) {
        _state.update {
            it.copy(
                level = user.tvShowGameLevel,
                points = user.totalPoints,
                questionCount = user.numTvShowQuestionsPassed + 1,
                isError = false,
                isLoading = false
            )
        }
    }

    override suspend fun levelUpUseCase() {
        levelUpTvShowUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updateTvShowsQuestionCountUseCase(questionCount)
    }
}
