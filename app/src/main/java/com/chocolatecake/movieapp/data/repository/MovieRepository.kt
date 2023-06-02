package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    suspend fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>>

    suspend fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    suspend fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>


    /// region search history
    fun getSearchHistory(keyword: String): Flow<List<SearchHistory>>
    suspend fun insertSearchHistory(keyword: String)
    suspend fun clearAllSearchHistory()
    suspend fun deleteSearchHistory(keyword: String)
    ///endregion
}