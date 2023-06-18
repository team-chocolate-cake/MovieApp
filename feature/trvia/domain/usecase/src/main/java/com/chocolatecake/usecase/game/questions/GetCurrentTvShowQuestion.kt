package com.chocolatecake.usecase.game.questions

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentTvShowQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.tvShowGameLevel
        val questionNumber = user.numTvShowQuestionsPassed
        return triviaRepository.getTvShowQuestion(level, questionNumber)
    }
}