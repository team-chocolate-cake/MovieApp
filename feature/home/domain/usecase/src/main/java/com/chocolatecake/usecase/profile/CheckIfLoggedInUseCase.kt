package com.chocolatecake.usecase.profile

import com.chocolatecake.repository.AccountRepository
import javax.inject.Inject

class CheckIfLoggedInUseCase  @Inject constructor(private val accountRepository: AccountRepository) {
    operator fun invoke() : Boolean{
        return !accountRepository.getSessionId().isNullOrBlank()
    }
}