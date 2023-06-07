package com.chocolatecake.movieapp.domain.usecases.auth

import com.chocolatecake.movieapp.domain.repository.AuthRepository
import com.chocolatecake.movieapp.domain.usecases.auth.model.LoginInputErrors
import com.chocolatecake.movieapp.domain.usecases.auth.model.LoginStateIndicator
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository,
    private val getIsValidLoginUseCase: GetIsValidLoginUseCase
) {
    suspend operator fun invoke(username: String, password: String): LoginStateIndicator {
        return when (getIsValidLoginUseCase(username, password)) {
            LoginInputErrors.USER_NAME_ERROR -> LoginStateIndicator.USER_NAME_ERROR
            LoginInputErrors.PASSWORD_ERROR -> LoginStateIndicator.PASSWORD_ERROR
            LoginInputErrors.NO_INPUT_ERRORS -> tryToLogin(username, password)
        }
    }

    private suspend fun tryToLogin(username: String, password: String): LoginStateIndicator {
        return try {
            authRepository.login(username, password)
            LoginStateIndicator.SUCCESS
        } catch (throwable: Throwable) {
            LoginStateIndicator.REQUEST_ERROR
        }
    }
}