package com.chocolatecake.movieapp.data.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.movieapp.data.local.database.entity.GenresMoviesEntity
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    /// region Movies
    //popular
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMovieEntity>)

    @Query("select * from POPULAR_MOVIE_TABLE")
    fun getPopularMovies(): Flow<List<PopularMovieEntity>>

    @Query("delete from POPULAR_MOVIE_TABLE")
    suspend fun clearAllPopularMovies()

    //now playing
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<NowPlayingMovieEntity>)

    @Query("select * from NOW_PLAYING_MOVIE_TABLE")
    fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>>

    @Query("delete from NOW_PLAYING_MOVIE_TABLE")
    suspend fun clearAllNowPlayingMovies()

    //top rated
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<TopRatedMovieEntity>)

    @Query("select * from TOP_RATED_MOVIE_TABLE")
    fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>>

    @Query("delete from TOP_RATED_MOVIE_TABLE")
    suspend fun clearAllTopRatedMovies()


    //upcoming
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(movies: List<UpcomingMovieEntity>)

    @Query("select * from UPCOMING_MOVIE_TABLE")
    fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>>

    @Query("delete from UPCOMING_MOVIE_TABLE")
    suspend fun clearAllUpcomingMovies()
    /// endregion


    ///region search history
    @Query("select * from SEARCH_HISTORY_TABLE WHERE keyword LIKE :keyword")
    fun getSearchHistory(keyword: String): List<SearchHistoryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(searchHistory: SearchHistoryEntity)

    @Query("delete from SEARCH_HISTORY_TABLE")
    suspend fun clearAllSearchHistory()

    @Query("delete from SEARCH_HISTORY_TABLE where keyword like :keyword")
    suspend fun deleteSearchHistory(keyword: String)
    ///endregion


    ///region search
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchMovies(movies: List<MovieEntity>)

    @Query("select * from MOVIE_TABLE")
    fun getSearchMovie(): Flow<List<MovieEntity>>
    ///endregion

    //region genres
    //movies
    @Query("select * from GENRES_MOVIES_TABLE")
    fun getGenresMovies(): List<GenresMoviesEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresMovies(genresMovies: List<GenresMoviesEntity>)

    @Query("delete from GENRES_MOVIES_TABLE")
    suspend fun clearAllGenresMovies()
    //endregion
}