package com.chocolatecake.usecase

import com.chocolatecake.entities.UserEntity
import com.chocolatecake.repository.AuthRepository
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.repository.UnauthorizedThrowable
import javax.inject.Inject

class GetCurrentUserUseCase @Inject constructor(
    private val triviaRepository: TriviaRepository,
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): UserEntity {
        return authRepository.getCurrentUsername()?.let {
            triviaRepository.getUserByUsername(it)
        } ?: throw UnauthorizedThrowable()
    }
}