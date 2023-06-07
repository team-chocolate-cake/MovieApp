package com.chocolatecake.movieapp.domain.usecases.auth

import com.chocolatecake.movieapp.domain.usecases.auth.model.LoginInputErrors
import javax.inject.Inject

class GetIsValidLoginUseCase @Inject constructor(){
     operator fun invoke(username: String, password: String): LoginInputErrors {
        return when {
            username.isEmpty() -> LoginInputErrors.USER_NAME_ERROR
            password.isEmpty() -> LoginInputErrors.PASSWORD_ERROR
            else -> LoginInputErrors.NO_INPUT_ERRORS
        }
    }
}

