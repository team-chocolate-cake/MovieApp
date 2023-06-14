package com.chocolatecake.local


interface PreferenceStorage {
    val sessionId: String?
    val lastRefreshTime: Long?

    suspend fun setSessionId(sessionId: String)
    suspend fun setLastRefreshTime(lastRefreshTime: Long)

    suspend fun clearPreferenceStorage()
}