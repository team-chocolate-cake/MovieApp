package com.chocolatecake.movieapp.data.local.prefs


interface PreferenceStorage {
    val sessionId: String?

    suspend fun setSessionId(sessionId: String)

    suspend fun clearPreferenceStorage()
}