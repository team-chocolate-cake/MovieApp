package com.chocolatecake.local


interface PreferenceStorage {
    val sessionId: String?
    val currentUserName: String?

    suspend fun setSessionId(sessionId: String)
    suspend fun setCurrentUserName(currentUserName: String)

    suspend fun clearPreferenceStorage()
}