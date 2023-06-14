package com.chocolatecake.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.local.database.dto.movie.NowPlayingMovieLocalDto
import com.chocolatecake.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.local.database.dto.movie.RecommendedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TrendingMoviesLocalDto
import com.chocolatecake.local.database.dto.movie.UpcomingMovieLocalDto

@Database(
    entities = [
        PopularMovieLocalDto::class,
        TopRatedMovieLocalDto::class,
        UpcomingMovieLocalDto::class,
        NowPlayingMovieLocalDto::class,
        RecommendedMovieLocalDto::class,
        TrendingMoviesLocalDto::class,
        PopularPeopleLocalDto::class,
        SearchHistoryLocalDto::class,
        GenresMoviesLocalDto::class,
        MovieLocalDto::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao
}