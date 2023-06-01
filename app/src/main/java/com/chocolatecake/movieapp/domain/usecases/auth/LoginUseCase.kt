package com.chocolatecake.movieapp.domain.usecases.auth

import com.chocolatecake.movieapp.data.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): Boolean{
        TODO()
    }
}