package com.chocolatecake.movieapp.data.local.prefs

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PreferenceStorageIml @Inject constructor(
    @ApplicationContext context: Context
) : PreferenceStorage {

    private val Context.preferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        "AppPrefStorage"
    )

    private val dataStore = context.preferencesDataStore

    private object PreferencesKeys {
        val SESSION_ID = stringPreferencesKey("SESSION_ID")
    }

    override val sessionId: String?
        get() = runBlocking { dataStore.data.map { it[PreferencesKeys.SESSION_ID] }.first() }

    override fun setSessionId(sessionId: String) {
        dataStore.setValue(PreferencesKeys.SESSION_ID, sessionId)
    }

    override fun clearPreferenceStorage() {
        runBlocking { dataStore.edit { it.clear() } }
    }

    private fun <T> DataStore<Preferences>.setValue(
        key: Preferences.Key<T>,
        value: T
    ) {
       runBlocking {
           this@setValue.edit { preferences ->
               preferences[key] = value
           }
       }
    }
}