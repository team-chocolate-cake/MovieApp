package com.chocolatecake.movieapp.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity

@Database(
    entities = [
        PopularMovieEntity::class,
        TopRatedMovieEntity::class,
        UpcomingMovieEntity::class,
        NowPlayingMovieEntity::class,
        SearchHistoryEntity::class,
        MovieEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao
}