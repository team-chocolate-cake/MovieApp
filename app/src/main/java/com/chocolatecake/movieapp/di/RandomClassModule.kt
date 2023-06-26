package com.chocolatecake.movieapp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.Random
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RandomClassModule {

    @Singleton
    @Provides
    fun provideRandom(): Random {
        return Random()
    }
}