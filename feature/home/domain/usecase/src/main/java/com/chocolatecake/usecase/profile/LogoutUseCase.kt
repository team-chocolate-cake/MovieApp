package com.chocolatecake.usecase.profile

import com.chocolatecake.repository.AuthRepository
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