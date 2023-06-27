package com.chocolatecake.local.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.local.database.dto.GenresTvsLocalDto
import com.chocolatecake.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.local.database.dto.SearchHistoryLocalDto
import com.chocolatecake.local.database.dto.movie.MovieInWatchHistoryLocalDto
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.local.database.dto.movie.NowPlayingMovieLocalDto
import com.chocolatecake.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.local.database.dto.movie.RecommendedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.local.database.dto.movie.TrendingMoviesLocalDto
import com.chocolatecake.local.database.dto.movie.UpcomingMovieLocalDto

@Dao
interface MovieDao {

    /// region Movies
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovies(movies: List<PopularMovieLocalDto>)

    @Query("select * from POPULAR_MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getPopularMovies(): List<PopularMovieLocalDto>

    @Query("delete from POPULAR_MOVIE_TABLE")
    suspend fun clearAllPopularMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<NowPlayingMovieLocalDto>)

    @Query("select * from NOW_PLAYING_MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getNowPlayingMovies(): List<NowPlayingMovieLocalDto>

    @Query("delete from NOW_PLAYING_MOVIE_TABLE")
    suspend fun clearAllNowPlayingMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopRatedMovies(movies: List<TopRatedMovieLocalDto>)

    @Query("select * from TOP_RATED_MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getTopRatedMovies(): List<TopRatedMovieLocalDto>

    @Query("delete from TOP_RATED_MOVIE_TABLE")
    suspend fun clearAllTopRatedMovies()


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(movies: List<UpcomingMovieLocalDto>)

    @Query("select * from UPCOMING_MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getUpcomingMovies(): List<UpcomingMovieLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecommendedMovies(movies: List<RecommendedMovieLocalDto>)

    @Query("select * from RECOMMENDED_MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getRecommendedMovie(): List<RecommendedMovieLocalDto>

    @Query("delete from UPCOMING_MOVIE_TABLE")
    suspend fun clearAllUpcomingMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTrendingMovies(movies: List<TrendingMoviesLocalDto>)

    @Query("select * from TRENDING_MOVIES_TABLE ORDER BY RANDOM()")
    suspend fun getTrendingMovies(): List<TrendingMoviesLocalDto>

    @Query("delete from TRENDING_MOVIES_TABLE")
    suspend fun clearAllTrendingMovies()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchMovies(movies: List<MovieLocalDto>)

    @Query("select * from MOVIE_TABLE ORDER BY RANDOM()")
    suspend fun getSearchMovie(): List<MovieLocalDto>
    /// endregion


    ///region People
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularPeople(people: List<PopularPeopleLocalDto>)

    @Query("select * from POPULAR_PEOPLE_TABLE ORDER BY RANDOM()")
    suspend fun getPopularPeople(): List<PopularPeopleLocalDto>

    @Query("delete from POPULAR_PEOPLE_TABLE")
    suspend fun clearAllPopularPeople()
    /// endregion


    ///region search history
    @Query("select * from SEARCH_HISTORY_TABLE WHERE keyword LIKE :keyword")
    suspend fun getSearchHistory(keyword: String): List<SearchHistoryLocalDto>

    @Query("select * from SEARCH_HISTORY_TABLE ORDER BY keyword ASC LIMIT 10")
    suspend fun getSearchHistory(): List<SearchHistoryLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSearchHistory(searchHistory: SearchHistoryLocalDto)

    @Query("delete from SEARCH_HISTORY_TABLE")
    suspend fun clearAllSearchHistory()

    @Query("delete from SEARCH_HISTORY_TABLE where keyword like :keyword")
    suspend fun deleteSearchHistory(keyword: String)
    ///endregion


    //region genres
    @Query("select * from GENRES_MOVIES_TABLE")
    suspend fun getGenresMovies(): List<GenresMoviesLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresMovies(genresMovies: List<GenresMoviesLocalDto>)

    @Query("delete from GENRES_MOVIES_TABLE")
    suspend fun clearAllGenresMovies()

    @Query("select * from GENRES_TVS_TABLE")
    suspend fun getGenresTvs(): List<GenresTvsLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGenresTvs(genresMovies: List<GenresTvsLocalDto>)

    @Query("delete from GENRES_TVS_TABLE")
    suspend fun clearAllGenresTvs()
    //endregion

    // region watch history
    @Query("select * from WATCH_HISTORY_MOVIES_TABLE")
    suspend fun getAllWatchHistoryVideos(): List<MovieInWatchHistoryLocalDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieToWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryLocalDto)

    @Delete
    suspend fun deleteMovieFromWatchHistory(movieInWatchHistoryEntity: MovieInWatchHistoryLocalDto)

    @Query("select * from WATCH_HISTORY_MOVIES_TABLE where title like :keyword")
    suspend fun searchWatchHistory(keyword: String): List<MovieInWatchHistoryLocalDto>

    // endregion
}