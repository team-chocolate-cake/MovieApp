package com.chocolatecake.movieapp.data.repository.auth

interface AuthRepository {

    @Throws(exceptionClasses = [NoTokenException::class])
    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()
}