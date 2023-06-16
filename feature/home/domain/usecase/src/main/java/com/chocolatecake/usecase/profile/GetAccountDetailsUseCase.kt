package com.chocolatecake.usecase.profile

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.repository.AccountRepository
import javax.inject.Inject

class GetAccountDetailsUseCase @Inject constructor(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke(): List<ProfileEntity> {
        return accountRepository.getAccountDetails()
    }
}