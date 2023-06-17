package com.chocolatecake.usecase.game.questions

import com.chocolatecake.entities.UserEntity
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class UpdateTvShowsQuestionCountUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(numTvShowQuestionsPassed: Int): Boolean {
        val oldUser = getCurrentUserUseCase()
        val maxQuestionCount = getMaxQuestionCountForUser(oldUser)
        if (numTvShowQuestionsPassed > maxQuestionCount) return false

        val updatedUser = oldUser.copy(numTvShowQuestionsPassed = numTvShowQuestionsPassed)
        triviaRepository.updateUser(updatedUser)
        return true
    }

    private fun getMaxQuestionCountForUser(oldUser: UserEntity): Int {
        return when (oldUser.tvShowGameLevel) {
            1 -> MAX_QUESTIONS_COUNT_IN_LEVEL1
            2 -> MAX_QUESTIONS_COUNT_IN_LEVEL2
            else -> MAX_QUESTIONS_COUNT_IN_LEVEL3
        }
    }

    private companion object {
        const val MAX_QUESTIONS_COUNT_IN_LEVEL1 = 5
        const val MAX_QUESTIONS_COUNT_IN_LEVEL2 = 10
        const val MAX_QUESTIONS_COUNT_IN_LEVEL3 = 15

    }
}