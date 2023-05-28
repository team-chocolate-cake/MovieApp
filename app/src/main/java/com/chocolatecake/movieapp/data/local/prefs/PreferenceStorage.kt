package com.chocolatecake.movieapp.data.local.prefs


interface PreferenceStorage {
    val sessionId: String?

    fun setSessionId(sessionId: String)

    fun clearPreferenceStorage()
}