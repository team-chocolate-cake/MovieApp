package com.chocolatecake.repository

import androidx.paging.Pager
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.RatingEntity
import com.chocolatecake.entities.season_details.SeasonDetailsEntity


interface MovieRepository {


    //showMore
    suspend fun getPopularMoviesPaging(): Pager<Int, MovieEntity>
    suspend fun getTopRateMoviesPaging(): Pager<Int, MovieEntity>
    suspend fun getTrendingMoviesPaging(): Pager<Int, MovieEntity>
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
    suspend fun searchHistory(): List<String>
    suspend fun insertSearchHistory(keyword: String)
    suspend fun clearAllSearchHistory()
    suspend fun deleteSearchHistory(keyword: String)

    suspend fun searchForMovies(keyword: String ): List<MovieEntity>

    suspend fun searchForTv(keyword: String): List<TvEntity>

    suspend fun searchForPeople(keyword: String): List<PeopleEntity>
    suspend fun getSearchMovies(keyword: String): List<MovieEntity>

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

    /// region season details
    suspend fun getSeasonDetails(seriesId : Int, seasonId : Int): SeasonDetailsEntity

    /// endregion
    suspend fun getTvDetailsInfo(tvShowID: Int): TvDetailsInfoEntity
    suspend fun getTvDetailsSeasons(tvShowID: Int): List<SeasonEntity>
    suspend fun getTvDetailsCredit(tvShowID: Int): List<PeopleEntity>
    suspend fun rateTvShow(rate: Double, tvShowID: Int): StatusEntity
    suspend fun getTvShowReviews(tvShowID: Int): List<ReviewEntity>
    suspend fun getTvShowRecommendations(tvShowID: Int): List<TvShowEntity>
    suspend fun getTvShowYoutubeDetails(tvShowID: Int): YoutubeVideoDetailsEntity

    suspend fun getUserLists(): List<UserListEntity>
    suspend fun postUserLists(listId: Int, mediaId: Int): StatusEntity
    suspend fun createUserList(listName:String):StatusEntity
}