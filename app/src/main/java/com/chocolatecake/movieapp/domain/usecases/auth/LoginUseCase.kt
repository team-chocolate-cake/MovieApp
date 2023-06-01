package com.chocolatecake.movieapp.domain.usecases.auth

import android.util.Log
import com.chocolatecake.movieapp.data.repository.auth.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(username: String, password: String): ValidationResult {
        return if (username.isNotEmpty() && password.isNotEmpty()) {
            authRepository.login(username, password)
            ValidationResult(true)
        } else {
            ValidationResult(false, "please fill all fields")
        }
    }
}