package com.chocolatecake.movieapp.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendedMovies(movies: List<RecommendedMovieEntity>)

    @Query("select * from RECOMMENDED_MOVIE_TABLE")
    fun getRecommendedMovie(): Flow<List<RecommendedMovieEntity>>

    @Query("delete from UPCOMING_MOVIE_TABLE")
    suspend fun clearAllUpcomingMovies()
    /// endregion

    ///region People
    //popular
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularPeople(people: List<PopularPeopleEntity>)

    @Query("select * from POPULAR_PEOPLE_TABLE")
    fun getPopularPeople(): Flow<List<PopularPeopleEntity>>

    @Query("delete from POPULAR_PEOPLE_TABLE")
    suspend fun clearAllPopularPeople()
}
    /// endregion


    ///trending
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMoviesEntity>)

    @Query("select * from TRENDING_MOVIES_TABLE")
    fun getTrendingMovies(): Flow<List<TrendingMoviesEntity>>

    @Query("delete from TRENDING_MOVIES_TABLE")
    suspend fun clearAllTrendingMovies()
    ///endregion

 }