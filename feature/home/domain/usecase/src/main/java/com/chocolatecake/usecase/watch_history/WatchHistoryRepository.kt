package com.chocolatecake.usecase.watch_history

import com.chocolatecake.entities.MovieInWatchHistoryEntity

interface WatchHistoryRepository {
    suspend fun insertMovieToWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryEntity)
    suspend fun deleteMovieFromWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryEntity)
    suspend fun getAllMoviesInWatchHistory(): List<MovieInWatchHistoryEntity>
    suspend fun searchWatchHistoryWithKeyWord(keyword: String): List<MovieInWatchHistoryEntity>
}