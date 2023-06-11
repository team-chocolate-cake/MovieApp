package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalRecommendedMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalTopRatedMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalTrendingMoviesMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalUpcomingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.people.LocalPopularPeopleMapper
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.remote.response.TvDto
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.base.BaseRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.domain.mappers.search_history.SearchHistoryUIMapper
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val popularMovieMapper: LocalPopularMovieMapper,
    private val nowPlayingMovieMapper: LocalNowPlayingMovieMapper,
    private val topRatedMovieMapper: LocalTopRatedMovieMapper,
    private val upComingMovieMapper: LocalUpcomingMovieMapper,
    private val searchHistoryMapper: SearchHistoryUIMapper,
    private val searchMovieUIMapper: MovieUIMapper,
    private val searchMovieMapper: LocalMovieMapper,
    private val recommendedMovieMapper: LocalRecommendedMovieMapper,
    private val trendingMoviesMapper: LocalTrendingMoviesMapper,
    private val popularPeopleMapper: LocalPopularPeopleMapper
) : BaseRepository(), MovieRepository {

    override suspend fun getPopularMovies(): Flow<List<PopularMovieEntity>> {
        refreshPopularMovies()
        return movieDao.getPopularMovies()
    }

    private suspend fun refreshPopularMovies() {
        refreshWrapper(
            service::getPopularMovies,
            popularMovieMapper::map,
            movieDao::insertPopularMovies
        )
    }

    override suspend fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>> {
        refreshNowPlayingMovies()
        return movieDao.getNowPlayingMovies()
    }

    private suspend fun refreshNowPlayingMovies() {
        refreshWrapper(
            service::getNowPlayingMovies,
            nowPlayingMovieMapper::map,
            movieDao::insertNowPlayingMovies
        )
    }

    override suspend fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>> {
        refreshTopRatedMovies()
        return movieDao.getTopRatedMovies()
    }

    private suspend fun refreshTopRatedMovies() {
        refreshWrapper(
            service::getTopRatedMovies,
            topRatedMovieMapper::map,
            movieDao::insertTopRatedMovies
        )
    }

    override suspend fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> {
        refreshUpcomingMovies()
        return movieDao.getUpcomingMovies()
    }


    private suspend fun refreshUpcomingMovies() {
        refreshWrapper(
            service::getUpcomingMovies,
            upComingMovieMapper::map,
            movieDao::insertUpcomingMovies
        )
    }

    override suspend fun getRecommendedMovies(): Flow<List<RecommendedMovieEntity>> {
        refreshRecommendedMovies()
        return movieDao.getRecommendedMovie()
    }

    private suspend fun refreshRecommendedMovies() {
//        refreshWrapper(
//            service::getRecommendedMovies,
//            recommendedMovieMapper::map,
//            movieDao::insertRecommendedMovies
//        )
    }

    override suspend fun getTrendingMovies(): Flow<List<TrendingMoviesEntity>> {
        refreshTrendingMovies()
            return movieDao.getTrendingMovies()
    }

    private suspend fun refreshTrendingMovies(){
        refreshWrapper(
            {service.getTrendingMovies()},
            trendingMoviesMapper::map,
            movieDao::insertTrendingMovies
        )
    }

    override suspend fun getPopularPeople(): Flow<List<PopularPeopleEntity>> {
        refreshPopularPeople()
        return movieDao.getPopularPeople()
    }

    private suspend fun refreshPopularPeople() {
        refreshWrapper(
            service::getPopularPeople,
            popularPeopleMapper::map,
            movieDao::insertPopularPeople
        )
    }


    ///region search history
    override suspend fun getSearchHistory(keyword: String): List<SearchHistoryEntity> {
        return movieDao.getSearchHistory("%${keyword}%")

    }

    override suspend fun insertSearchHistory(searchHistory: SearchHistoryEntity) {
        return movieDao.insertSearchHistory(searchHistory)
    }

    override suspend fun clearAllSearchHistory() {
        return movieDao.clearAllSearchHistory()
    }

    override suspend fun deleteSearchHistory(keyword: String) {
        movieDao.deleteSearchHistory(keyword)
    }
    ///endregion


    ///region search movies
    override suspend fun getSearchMovies(keyword: String): List<MovieDto> {
        return wrapApiCall { service.searchForMovies(keyword) }.results
            ?.filterNotNull() ?: emptyList()
    }

    override suspend fun searchForTv(keyword: String): List<TvDto> {
        return wrapApiCall { service.searchForTv(keyword) }.results
            ?.filterNotNull() ?: emptyList()
    }
    //endregion
}