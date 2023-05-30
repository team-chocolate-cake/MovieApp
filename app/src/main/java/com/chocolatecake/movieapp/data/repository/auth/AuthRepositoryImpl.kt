package com.chocolatecake.movieapp.data.repository.auth

import com.chocolatecake.movieapp.data.local.prefs.PreferenceStorage
import com.chocolatecake.movieapp.data.remote.request.LoginRequest
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.base.UnauthorizedThrowable
import com.chocolatecake.movieapp.data.repository.base.BaseRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val prefs: PreferenceStorage
) : BaseRepository(), AuthRepository {

    override suspend fun login(username: String, password: String): Boolean {
        val token = getRequestToken()
        val body = LoginRequest(username, password, token)

        return wrapApiCall { service.login(body) }
            .requestToken?.let { createSession(it); true } ?: false
    }

    private suspend fun createSession(requestToken: String) {
        wrapApiCall { service.createSession(requestToken) }
            .takeIf { it.isSuccess == true }
            ?.sessionId?.let { prefs.setSessionId(it) }
    }

    private suspend fun getRequestToken(): String {
        return wrapApiCall { service.createRequestToken() }
            .requestToken ?: throw UnauthorizedThrowable()
    }

    override suspend fun logout() {
        prefs.setSessionId("")
    }
}

