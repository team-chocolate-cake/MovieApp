package com.chocolatecake.movieapp.domain.repository

import com.chocolatecake.movieapp.domain.model.Genre
import com.chocolatecake.movieapp.domain.model.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>

    suspend fun getNowPlayingMovies(): List<Movie>

    suspend fun getTopRatedMovies(): List<Movie>

    suspend fun getUpcomingMovies(): List<Movie>

    suspend fun getPopularPeople() : List<Movie>

    suspend fun getRecommendedMovies(): List<Movie>

    suspend fun getTrendingMovies(): List<Movie>

    suspend fun getSearchHistory(keyword: String): List<String>

    suspend fun insertSearchHistory(searchHistory: String)

    suspend fun clearAllSearchHistory()

    suspend fun deleteSearchHistory(keyword: String)

    suspend fun getSearchMovies(keyword: String ): List<Movie>

    suspend fun getGenresMovies(): List<Genre>

}