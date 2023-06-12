package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.mappers.PopularMoviesUiMapper
import com.chocolatecake.movieapp.data.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.data.mappers.search_history.SearchHistoryUIMapper
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.mappers.genres.LocalGenresMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalRecommendedMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalTopRatedMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalTrendingMoviesMapper
import com.chocolatecake.movieapp.data.repository.mappers.movie.LocalUpcomingMovieMapper
import com.chocolatecake.movieapp.data.repository.mappers.people.LocalPopularPeopleMapper
import com.chocolatecake.movieapp.domain.entities.GenreEntity
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import com.chocolatecake.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val genresMovieMapper: LocalGenresMovieMapper,
    private val popularMovieMapper: LocalPopularMovieMapper,
    private val popularMoviesUiMapper: PopularMoviesUiMapper,
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

    override suspend fun getPopularMovies(): List<HomeMovie> {
        return popularMoviesUiMapper.map(movieDao.getPopularMovies())
    }

    override suspend fun refreshPopularMovies() {
        refreshWrapper(
            service::getPopularMovies,
            popularMovieMapper::map,
            movieDao::insertPopularMovies
        )
    }

    override suspend fun getNowPlayingMovies(): List<MovieEntity> {
        return movieDao.getNowPlayingMovies()
    }

    override suspend fun refreshNowPlayingMovies() {
        refreshWrapper(
            service::getNowPlayingMovies,
            nowPlayingMovieMapper::map,
            movieDao::insertNowPlayingMovies
        )
    }

    override suspend fun getTopRatedMovies(): List<MovieEntity> {
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

    override suspend fun getUpcomingMovies(): List<MovieEntity> {
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

    override suspend fun getRecommendedMovies(): List<MovieEntity> {
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

    override suspend fun getTrendingMovies(): List<MovieEntity> {
        refreshTrendingMovies()
        return movieDao.getTrendingMovies()
    }

    private suspend fun refreshTrendingMovies() {
        refreshWrapper(
            { service.getTrendingMovies() },
            trendingMoviesMapper::map,
            movieDao::insertTrendingMovies
        )
    }

    override suspend fun getPopularPeople(): List<MovieEntity> {
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
    override suspend fun getSearchHistory(keyword: String): List<String> {
        return movieDao.getSearchHistory("%${keyword}%")

    }

    override suspend fun insertSearchHistory(searchHistory: String) {
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
    override suspend fun getSearchMovies(keyword: String): List<MovieEntity> {
        genresMovieMapper.map(
            wrapApiCall { service.getListOfGenresForMovies() }.results ?: emptyList()
        ).let {
            wrapApiCall { service.getSearchMovies(keyword) }.results
                searchMovieMapper
        }
        return
            ?.filterNotNull() ?: emptyList()
    }
    //endregion

    override suspend fun getGenresMovies(): List<GenreEntity> {
        return service.getListOfGenresForMovies().body()?.results
    }
}
