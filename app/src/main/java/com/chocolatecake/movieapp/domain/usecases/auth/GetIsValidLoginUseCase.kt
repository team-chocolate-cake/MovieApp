package com.chocolatecake.movieapp.domain.usecases.auth

import com.chocolatecake.movieapp.data.repository.auth.AuthRepository
import javax.inject.Inject

class GetIsValidLoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): LoginStateIndicator {
        return if (username.isEmpty()) {
            LoginStateIndicator.USER_NAME_ERROR
        } else if (password.isEmpty()) {
            LoginStateIndicator.PASSWORD_NAME_ERROR
        } else {
            try {
                authRepository.login(username, password)
                LoginStateIndicator.SUCCESS
            } catch (th: Throwable) {
                LoginStateIndicator.REQUEST_ERROR
            }
        }
    }
}

enum class LoginStateIndicator {
    USER_NAME_ERROR,
    PASSWORD_NAME_ERROR,
    REQUEST_ERROR,
    SUCCESS
}