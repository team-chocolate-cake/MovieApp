package com.chocolatecake.movieapp.di

import com.chocolatecake.repository.AuthRepository
import com.chocolatecake.repository.MovieRepository
import com.chocolatecake.repository.MovieRepositoryImpl
import com.chocolatecake.repository.TriviaRepository
import com.chocolatecake.repository.TriviaRepositoryIml
import com.chocolatecake.repository.WatchHistoryRepositoryImpl
import com.chocolatecake.repository.auth.AuthRepositoryImpl
import com.chocolatecake.usecase.watch_history.WatchHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    @ViewModelScoped
    abstract fun bindAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    @ViewModelScoped
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @ViewModelScoped
    abstract fun bindTriviaRepository(triviaRepositoryImpl: TriviaRepositoryIml): TriviaRepository

    @Binds
    @ViewModelScoped abstract fun bindWatchHistoryRepository(watchHistoryRepositoryImpl: WatchHistoryRepositoryImpl): WatchHistoryRepository

}