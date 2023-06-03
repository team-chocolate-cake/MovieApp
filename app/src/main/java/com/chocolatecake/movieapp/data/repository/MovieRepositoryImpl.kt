package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalTopRatedMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalUpcomingMovieMapper
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.base.BaseRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.domain.mappers.search_history.SearchHistoryUIMapper
import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
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


    ///region search history
    override fun getSearchHistory(keyword: String): Flow<List<SearchHistory>> {
        return  movieDao.getSearchHistory("%${keyword}%").map { items->
            items.map {
                searchHistoryMapper.map(it)
            }
        }
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
    override suspend fun getSearchMovies(keyword: String ): Flow<List<Movie>> {
         refreshSearchMovies(keyword)
        return movieDao.getSearchMovie().map { items->
            items.map { searchMovieUIMapper.map(it) }
        }
    }
    private suspend fun refreshSearchMovies(keyword: String) {
         refreshWrapper(
             apiCall = { service.getSearchMovies(keyword)},
             localMapper = searchMovieMapper::map,
             databaseSaver = movieDao::insertSearchMovies
        )
    }
    //endregion
}