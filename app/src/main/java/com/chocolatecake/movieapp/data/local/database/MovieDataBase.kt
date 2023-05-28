package com.chocolatecake.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolatecake.movieapp.data.local.database.dao.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.MovieEntity

@Database(
    entities = [MovieEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase: RoomDatabase() {
    abstract val movieDao: MovieDao
}