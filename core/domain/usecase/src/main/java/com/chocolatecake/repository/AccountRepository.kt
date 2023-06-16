package com.chocolatecake.repository

import com.chocolatecake.entities.ProfileEntity

interface AccountRepository {
    fun getSessionId(): String?
    suspend fun logout()
    suspend fun getAccountDetails():List<ProfileEntity>
}