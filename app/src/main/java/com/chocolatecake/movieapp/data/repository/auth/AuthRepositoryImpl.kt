package com.chocolatecake.movieapp.data.repository.auth

import com.chocolatecake.movieapp.data.remote.request.LoginRequest
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.BaseRepository
import com.chocolatecake.movieapp.data.repository.PreferenceStorage
import com.chocolatecake.movieapp.domain.repository.AuthRepository
import com.chocolatecake.movieapp.domain.repository.UnauthorizedThrowable
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val prefs: PreferenceStorage
) : BaseRepository(), AuthRepository {

    override suspend fun login(username: String, password: String): Boolean {
        val token = getRequestToken()
        val body = LoginRequest(username, password, token)

        return wrapApiCall { movieService.login(body) }
            .requestToken?.let { createSession(it); true } ?: false
    }

    private suspend fun createSession(requestToken: String) {
        wrapApiCall { movieService.createSession(requestToken) }
            .takeIf { it.isSuccess == true }
            ?.sessionId?.let { prefs.setSessionId(it) }
    }

    private suspend fun getRequestToken(): String {
        return wrapApiCall { movieService.createRequestToken() }
            .requestToken ?: throw UnauthorizedThrowable()
    }

    override suspend fun logout() {
        prefs.setSessionId("")
    }
}

