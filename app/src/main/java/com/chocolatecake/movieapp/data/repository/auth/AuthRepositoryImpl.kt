package com.chocolatecake.movieapp.data.repository.auth

import com.chocolatecake.movieapp.data.local.prefs.PreferenceStorage
import com.chocolatecake.movieapp.data.remote.request.LoginRequest
import com.chocolatecake.movieapp.data.remote.service.MovieService
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val prefs: PreferenceStorage
) : AuthRepository {

    @Throws(exceptionClasses = [NoTokenException::class])
    override suspend fun login(username: String, password: String): Boolean {
        val token = getRequestToken()
        val body = LoginRequest(username, password, token)

        return service.login(body).takeIf { it.isSuccessful }
            ?.body()
            ?.requestToken
            ?.let { createSession(it); true } ?: false
    }

    private suspend fun createSession(requestToken: String) {
        service.createSession(requestToken).takeIf { it.isSuccessful }
            ?.body()
            ?.takeIf { it.isSuccess == true }
            ?.sessionId
            ?.let { prefs.setSessionId(it) }
    }

    private suspend fun getRequestToken(): String {
        return service.createRequestToken()
            .takeIf { it.isSuccessful }
            ?.body()
            ?.requestToken ?: throw NoTokenException()
    }


    override suspend fun logout() {
        prefs.setSessionId("")
    }
}

