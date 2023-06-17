package com.chocolatecake.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


class PreferenceStorageIml @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : PreferenceStorage {

    private object PreferencesKeys {
        val CURRENT_USERNAME_ID = stringPreferencesKey("CURRENT_USERNAME_ID")
        val SESSION_ID = stringPreferencesKey("SESSION_ID")
        val LAST_REFRESH = longPreferencesKey("LAST_REFRESH")
    }

    override val sessionId: String?
        get() = runBlocking { dataStore.data.map { it[PreferencesKeys.SESSION_ID] }.first() }
    override val currentUserName: String?
        get() = runBlocking {
            dataStore.data.map { it[PreferencesKeys.CURRENT_USERNAME_ID] }.first()
        }

    override val lastRefreshTime: Long?
        get() = runBlocking { dataStore.data.map { it[PreferencesKeys.LAST_REFRESH] }.first() }

    override suspend fun setSessionId(sessionId: String) {
        dataStore.setValue(PreferencesKeys.SESSION_ID, sessionId)
    }

    override suspend fun setCurrentUserName(currentUserName: String) {
        dataStore.setValue(PreferencesKeys.CURRENT_USERNAME_ID, currentUserName)
    }

    override suspend fun setLastRefreshTime(lastRefreshTime: Long) {
        dataStore.setValue(PreferencesKeys.LAST_REFRESH, lastRefreshTime)
    }

    override suspend fun clearPreferenceStorage() {
        dataStore.edit { it.clear() }
    }

    private suspend fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
        this.edit { preferences ->
            preferences[key] = value
        }
    }
}
