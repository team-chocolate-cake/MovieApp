package com.chocolatecake.usecase.game.questions

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentPeopleQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.peopleGameLevel
        val questionNumber = user.numPeopleQuestionsPassed
        return triviaRepository.getPeopleQuestion(level, questionNumber)
    }
}