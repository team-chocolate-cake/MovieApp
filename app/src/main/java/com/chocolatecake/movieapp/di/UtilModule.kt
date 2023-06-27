package com.chocolatecake.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import java.util.Random
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilModule {

    @Provides
    @Singleton
    fun provideCurrentDate(): Date {
        return Date(System.currentTimeMillis())
    }

    @Provides
    @Singleton
    fun provideRandomClass(): Random {
        return Random()
    }
}