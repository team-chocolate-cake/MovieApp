package com.chocolatecake.usecase.game

import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class UpdateUserPointsUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(points: Int) {
        val oldUser = getCurrentUserUseCase()
        val updatedUser = oldUser.copy(
            totalPoints = oldUser.totalPoints + points
        )
        triviaRepository.updateUser(updatedUser)
    }
}
