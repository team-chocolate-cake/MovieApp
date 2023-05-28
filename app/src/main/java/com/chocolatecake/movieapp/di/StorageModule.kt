package com.chocolatecake.movieapp.di

import com.chocolatecake.movieapp.data.local.prefs.PreferenceStorage
import com.chocolatecake.movieapp.data.local.prefs.PreferenceStorageIml
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    @Singleton
    abstract fun bindPrefsDataStore(preferenceStorageIml: PreferenceStorageIml): PreferenceStorage
}