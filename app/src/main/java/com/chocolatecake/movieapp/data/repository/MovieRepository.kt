package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    suspend fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>>

    suspend fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    suspend fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>


    /// region search history
    suspend fun getSearchHistory(keyword: String): List<SearchHistoryEntity>
    suspend fun insertSearchHistory(searchHistory: SearchHistoryEntity)
    suspend fun clearAllSearchHistory()
    suspend fun deleteSearchHistory(keyword: String)
    ///endregion

    //region search movies
    suspend fun getSearchMovies(keyword: String ): List<MovieDto>
    ///endregion
}