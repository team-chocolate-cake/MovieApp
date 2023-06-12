package com.chocolatecake.movieapp.domain.repository

import com.chocolatecake.movieapp.domain.entities.GenreEntity
import com.chocolatecake.movieapp.domain.entities.MovieEntity

interface MovieRepository {

    suspend fun getPopularMovies(): List<SimpleMovieEntity>

    suspend fun getNowPlayingMovies(): List<SimpleMovieEntity>

    suspend fun getTopRatedMovies(): List<SimpleMovieEntity>

    suspend fun getUpcomingMovies(): List<SimpleMovieEntity>

    suspend fun getPopularPeople() : List<SimpleMovieEntity>

    suspend fun getRecommendedMovies(): List<SimpleMovieEntity>

    suspend fun getTrendingMovies(): List<SimpleMovieEntity>

    suspend fun getSearchHistory(keyword: String): List<String>

    suspend fun insertSearchHistory(searchHistory: String)

    suspend fun clearAllSearchHistory()

    suspend fun deleteSearchHistory(keyword: String)

    suspend fun getSearchMovies(keyword: String ): List<MovieEntity>

    suspend fun getGenresMovies(): List<GenreEntity>

    suspend fun refreshPopularMovies()
}