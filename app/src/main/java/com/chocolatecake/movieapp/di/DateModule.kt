package com.chocolatecake.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Date
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DateModule {

    @Provides
    @Singleton
    fun provideCurrentDate(): Date {
        return Date(System.currentTimeMillis())
    }
}