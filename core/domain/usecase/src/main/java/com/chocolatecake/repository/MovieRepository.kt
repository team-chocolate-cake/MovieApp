package com.chocolatecake.repository

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.entities.YoutubeVideoDetailsEntity


interface MovieRepository {

    suspend fun getPopularMovies(): List<MovieEntity>
    suspend fun refreshPopularMovies()

    suspend fun getNowPlayingMovies(): List<MovieEntity>
    suspend fun refreshNowPlayingMovies()

    suspend fun getTopRatedMovies(): List<MovieEntity>
    suspend fun refreshTopRatedMovies()

    suspend fun getUpcomingMovies(): List<MovieEntity>
    suspend fun refreshUpcomingMovies()

    suspend fun getPopularPeople(): List<PeopleEntity>
    suspend fun refreshTrendingMovies()

    suspend fun getTrendingMovies(): List<MovieEntity>
    suspend fun refreshPopularPeople()

    suspend fun getSearchHistory(keyword: String): List<String>
    suspend fun insertSearchHistory(keyword: String)
    suspend fun clearAllSearchHistory()
    suspend fun deleteSearchHistory(keyword: String)

    suspend fun getSearchMovies(keyword: String): List<MovieEntity>

    suspend fun getGenresMovies(): List<GenreEntity>
    suspend fun refreshGenres()

    suspend fun getTvDetailsInfo(tvShowID: Int ): TvDetailsInfoEntity
    suspend fun getTvDetailsSeasons(tvShowID: Int ): List<SeasonEntity>
    suspend fun getTvDetailsCredit(tvShowID: Int ): List<PeopleEntity>
    suspend fun rateTvShow(rate: Double, tvShowID: Int ): TvRatingEntity
    suspend fun getTvShowReviews(tvShowID: Int): List<ReviewEntity>
    suspend fun getTvShowRecommendations(tvShowID: Int):List<TvShowEntity>
    suspend fun getTvShowYoutubeDetails(tvShowID: Int): YoutubeVideoDetailsEntity
}