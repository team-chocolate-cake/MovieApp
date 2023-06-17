package com.chocolatecake.repository.auth

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.local.PreferenceStorage
import com.chocolatecake.remote.request.LoginRequest
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.AuthRepository
import com.chocolatecake.repository.BaseRepository
import com.chocolatecake.repository.UnauthorizedThrowable
import com.chocolatecake.repository.mappers.domain.DomainProfileMapper
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val movieService: MovieService,
    private val prefs: PreferenceStorage,
    private val domainProfileMapper: DomainProfileMapper
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

    override suspend fun getAccountDetails(): ProfileEntity {
        val sessionId=prefs.sessionId
        val profileData=movieService.getAccountDetails(sessionId!!).body()
        return domainProfileMapper.map(profileData!!)
    }
}

