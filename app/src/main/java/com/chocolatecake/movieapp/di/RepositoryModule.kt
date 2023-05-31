package com.chocolatecake.movieapp.di

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.data.repository.MovieRepositoryImpl
import com.chocolatecake.movieapp.data.repository.auth.AuthRepository
import com.chocolatecake.movieapp.data.repository.auth.AuthRepositoryImpl
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
}