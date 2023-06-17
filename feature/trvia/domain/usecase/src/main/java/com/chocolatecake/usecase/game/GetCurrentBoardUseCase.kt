package com.chocolatecake.usecase.game

import com.chocolatecake.entities.BoardEntity
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.usecase.GetCurrentUserUseCase
import javax.inject.Inject

class GetCurrentBoardUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) {
    suspend operator fun invoke(): BoardEntity {
        val user = getCurrentUserUseCase()
        val level = user.moviesGameLevel

        return triviaRepository.getBoard(level)
    }
}