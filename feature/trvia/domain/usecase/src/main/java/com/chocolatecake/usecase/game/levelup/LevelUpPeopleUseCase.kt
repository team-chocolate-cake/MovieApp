package com.chocolatecake.usecase.game.levelup

import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class LevelUpPeopleUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) {
    suspend operator fun invoke(): Boolean {
        val oldUser = getCurrentUserUseCase()
        if (oldUser.peopleGameLevel == MAX_LEVEL) return false
        val updatedUser = oldUser.copy(
            peopleGameLevel = oldUser.peopleGameLevel + 1
        )
        triviaRepository.updateUser(updatedUser)
        return true
    }

    private companion object {
        const val MAX_LEVEL = 3
    }
}