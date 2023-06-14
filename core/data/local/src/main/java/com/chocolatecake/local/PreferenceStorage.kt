package com.chocolatecake.local


interface PreferenceStorage {
    val sessionId: String?

    suspend fun setSessionId(sessionId: String)

    suspend fun clearPreferenceStorage()
}