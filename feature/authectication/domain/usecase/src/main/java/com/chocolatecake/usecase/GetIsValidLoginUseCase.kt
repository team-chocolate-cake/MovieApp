package com.chocolatecake.usecase

import javax.inject.Inject

class GetIsValidLoginUseCase @Inject constructor() {
    operator fun invoke(username: String, password: String): LoginError {
        return when {
            username.isEmpty() -> LoginError.USER_NAME_ERROR
            password.isEmpty() -> LoginError.PASSWORD_ERROR
            else -> LoginError.NO_INPUT_ERRORS
        }
    }
}

