package com.chocolatecake.movieapp.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.movieapp.data.local.database.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Query("select * from MOVIE_TABLE")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("delete from MOVIE_TABLE")
    suspend fun clearAllMovies()
}