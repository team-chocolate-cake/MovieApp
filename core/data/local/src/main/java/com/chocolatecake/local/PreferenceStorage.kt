package com.chocolatecake.local


interface PreferenceStorage {
    val sessionId: String?
    val currentUserName: String?

    suspend fun setSessionId(sessionId: String)
    suspend fun setCurrentUserName(currentUserName: String)
    val lastRefreshTime: Long?

    suspend fun setLastRefreshTime(lastRefreshTime: Long)

    suspend fun clearPreferenceStorage()
}