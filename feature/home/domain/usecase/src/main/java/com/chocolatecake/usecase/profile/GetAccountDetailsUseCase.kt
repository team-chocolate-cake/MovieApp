package com.chocolatecake.usecase.profile

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.repository.AuthRepository
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: AuthRepository,
) {
    suspend operator fun invoke(): ProfileEntity {
        return accountRepository.getAccountDetails()
    }
}