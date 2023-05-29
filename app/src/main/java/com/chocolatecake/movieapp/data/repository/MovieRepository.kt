package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    suspend fun refreshPopularMovies()

    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>>

    suspend fun refreshNowPlayingMovies()

    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    suspend fun refreshTopRatedMovies()

    fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>

    suspend fun refreshUpcomingMovies()
}