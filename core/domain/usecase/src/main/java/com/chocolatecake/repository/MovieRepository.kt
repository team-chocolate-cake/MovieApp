package com.chocolatecake.repository

import androidx.paging.Pager
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.TvEntity
import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RatingEntity
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.entities.myList.WatchlistRequestEntity


interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieEntity>
    suspend fun refreshPopularMovies()

    suspend fun getNowPlayingMovies(): List<MovieEntity>
    suspend fun refreshNowPlayingMovies()

    suspend fun getTopRatedMovies(): List<MovieEntity>
    suspend fun refreshTopRatedMovies()

    suspend fun getUpcomingMovies(): List<MovieEntity>
    suspend fun refreshUpcomingMovies()

    suspend fun getPopularPeople() : List<PeopleEntity>
    suspend fun refreshTrendingMovies()

    suspend fun getTrendingMovies(): List<MovieEntity>
    suspend fun refreshPopularPeople()

    suspend fun getSearchHistory(keyword: String): List<String>
    suspend fun insertSearchHistory(keyword: String)
    suspend fun clearAllSearchHistory()
    suspend fun deleteSearchHistory(keyword: String)

    suspend fun searchForMovies(keyword: String ): List<MovieEntity>

    suspend fun searchForTv(keyword: String): List<TvEntity>

    suspend fun searchForPeople(keyword: String): List<PeopleEntity>

    suspend fun getGenresMovies(): List<GenreEntity>
    suspend fun refreshGenres()
    suspend fun getAiringTodayTVShows(): Pager<Int, TVShowsEntity>
    suspend fun getTopRatedTVShows(): Pager<Int, TVShowsEntity>
    suspend fun getPopularTVShows(): Pager<Int, TVShowsEntity>
    suspend fun getOnTheAirTVShows(): Pager<Int, TVShowsEntity>

    suspend fun getMoviesDetails(movieId:Int): MovieDetailsEntity
    suspend fun setMovieRate(movieId:Int , rate:Float): RatingEntity

    suspend fun getGenresTvs(): List<GenreEntity>
    suspend fun refreshGenresTv()
    suspend fun getLastRefreshTime(): Long?
    suspend fun setLastRefreshTime(time: Long)
    suspend fun refreshAll()



    suspend fun getFavoriteMovies(): List<MovieEntity>
    suspend fun refreshFavoriteMovies()
    suspend fun addFavoriteMovie(favoriteBody: FavoriteBodyRequestEntity): Boolean


    suspend fun getFavoriteByMediaType(mediaType: String): List<MovieEntity>

    suspend fun getWatchlistByMediaType(mediaType: String): List<MovieEntity>
    suspend fun addWatchlist(watchlistRequest: WatchlistRequestEntity): Boolean

}