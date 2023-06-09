package com.chocolatecake.movieapp.data.repository


interface PreferenceStorage {
    val sessionId: String?

    suspend fun setSessionId(sessionId: String)

    suspend fun clearPreferenceStorage()
}