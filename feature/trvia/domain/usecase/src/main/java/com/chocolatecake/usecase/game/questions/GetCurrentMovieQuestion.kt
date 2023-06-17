package com.chocolatecake.usecase.game.questions

import com.chocolatecake.entities.QuestionEntity
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentMovieQuestion @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): QuestionEntity {
        val user = getCurrentUserUseCase()
        val level = user.moviesGameLevel
        val questionNumber = user.numMoviesQuestionsPassed
        return triviaRepository.getMovieQuestion(level, questionNumber)
    }
}