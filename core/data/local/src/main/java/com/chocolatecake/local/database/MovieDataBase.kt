package com.chocolatecake.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.local.database.dto.GenresTvsLocalDto
import com.chocolatecake.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.local.database.dto.ProfileLocalDto
import com.chocolatecake.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.local.database.dto.UserLocalDto
import com.chocolatecake.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.chocolatecake.local.database.dto.movie.MovieListDetailsLocalDto
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.local.database.dto.movie.NowPlayingMovieLocalDto
import com.chocolatecake.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.local.database.dto.movie.RecommendedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TrendingMoviesLocalDto
import com.chocolatecake.local.database.dto.movie.UpcomingMovieLocalDto
import com.chocolatecake.local.database.dto.movie.WatchlistLocalDto
import com.chocolatecake.local.database.dto.myList.ListLocalDto
import com.chocolatecake.local.database.dto.myList.ListMovieLocalDto

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
        ProfileLocalDto::class,
        GenresTvsLocalDto::class,
        MovieLocalDto::class,
        UserLocalDto::class,
        MovieInWatchHistoryLocalDto::class,
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Convertors::class)
abstract class MovieDataBase : RoomDatabase() {
    abstract val movieDao: MovieDao
    abstract val triviaDao: TriviaDao
}