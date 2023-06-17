package com.chocolatecake.usecase.game.levelup

import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class LevelUpMoviesUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): Boolean {
        val oldUser = getCurrentUserUseCase()
        if (oldUser.moviesGameLevel == MAX_LEVEL) return false
        val updatedUser = oldUser.copy(
            moviesGameLevel = oldUser.moviesGameLevel + 1,
            numMoviesQuestionsPassed = 0
        )
        triviaRepository.updateUser(updatedUser)
        return true
    }

    private companion object {
        const val MAX_LEVEL = 3
    }
}