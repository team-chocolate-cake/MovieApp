package com.chocolatecake.movieapp.domain.repository

interface AuthRepository {

    @Throws(exceptionClasses = [ApiThrowable::class])
    suspend fun login(username: String, password: String): Boolean

    suspend fun logout()
}