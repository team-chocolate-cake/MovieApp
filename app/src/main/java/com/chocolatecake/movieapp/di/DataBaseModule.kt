package com.chocolatecake.movieapp.di

import android.content.Context
import androidx.room.Room
import com.chocolatecake.movieapp.data.local.database.MovieDataBase
import com.chocolatecake.movieapp.data.local.database.MovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun providesMovieDatabase(
        @ApplicationContext context: Context,
    ): MovieDataBase {
        return Room.databaseBuilder(
            context,
            MovieDataBase::class.java,
            "MovieDatabase.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(movieDataBase: MovieDataBase): MovieDao {
        return movieDataBase.movieDao
    }

}