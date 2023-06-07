package com.chocolatecake.movieapp.domain.usecases.auth

import com.chocolatecake.movieapp.domain.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean{
        return try{
            authRepository.logout()
            true
        }catch (_: Throwable){
            false
        }
    }
}