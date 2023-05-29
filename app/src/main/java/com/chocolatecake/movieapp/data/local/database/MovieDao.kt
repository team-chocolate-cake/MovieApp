package com.chocolatecake.movieapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMovieEntity>)

    @Query("select * from TRENDING_MOVIE_TABLE")
    fun getTrendingMovies(): Flow<List<TrendingMovieEntity>>

    @Query("delete from TRENDING_MOVIE_TABLE")
    suspend fun clearAllTrendingMovies()
}