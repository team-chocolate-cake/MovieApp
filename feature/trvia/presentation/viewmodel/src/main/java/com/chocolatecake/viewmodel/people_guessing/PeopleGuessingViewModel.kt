package com.chocolatecake.viewmodel.people_guessing

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.usecase.GetCurrentUserUseCase
import com.chocolatecake.usecase.game.UpdateUserPointsUseCase
import com.chocolatecake.usecase.game.levelup.LevelUpPeopleUseCase
import com.chocolatecake.usecase.game.questions.GetCurrentPeopleQuestion
import com.chocolatecake.usecase.game.questions.UpdatePeopleQuestionCountUseCase
import com.chocolatecake.viewmodel.common.BaseTriviaQuestionGameViewModel
import com.chocolatecake.viewmodel.common.model.GameUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PeopleGuessingViewModel @Inject constructor(
    override val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCurrentPeopleQuestion: GetCurrentPeopleQuestion,
    private val updatePeopleQuestionCountUseCase: UpdatePeopleQuestionCountUseCase,
    override val updateUserPointsUseCase: UpdateUserPointsUseCase,
    private val levelUpPeopleUseCase: LevelUpPeopleUseCase,
) : BaseTriviaQuestionGameViewModel(state = GameUiState()) {

    init {
        getData()
    }

    override val getQuestion: suspend () -> QuestionEntity
        get() = getCurrentPeopleQuestion::invoke

    override suspend fun levelUpUseCase() {
        levelUpPeopleUseCase()
    }

    override suspend fun updateQuestionCountUseCase(questionCount: Int) {
        updatePeopleQuestionCountUseCase(questionCount)
    }
}