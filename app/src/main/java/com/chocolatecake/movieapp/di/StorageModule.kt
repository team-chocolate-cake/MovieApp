package com.chocolatecake.movieapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.chocolatecake.local.PreferenceStorage
import com.chocolatecake.local.PreferenceStorageIml
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    @Provides
    @Singleton
    fun providePreferenceStorage(dataStore: DataStore<Preferences>): PreferenceStorage {
        return PreferenceStorageIml(dataStore)
    }

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences>{
       return PreferenceDataStoreFactory.create() {
            applicationContext.preferencesDataStoreFile("AppPrefStorage")
        }
    }
}