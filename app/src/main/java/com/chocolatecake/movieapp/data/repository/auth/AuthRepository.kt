package com.chocolatecake.movieapp.data.repository.auth

import com.chocolatecake.movieapp.data.repository.base.NoNetworkThrowable
import com.chocolatecake.movieapp.data.repository.base.UnauthorizedThrowable

interface AuthRepository {

    @Throws(exceptionClasses = [UnauthorizedThrowable::class, NoNetworkThrowable::class])
    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()
}