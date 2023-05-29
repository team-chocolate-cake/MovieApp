package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalTopRatedMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalUpcomingMovieMapper
import com.chocolatecake.movieapp.data.remote.service.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val popularMovieMapper: LocalPopularMovieMapper,
    private val nowPlayingMovieMapper: LocalNowPlayingMovieMapper,
    private val topRatedMovieMapper: LocalTopRatedMovieMapper,
    private val upComingMovieMapper: LocalUpcomingMovieMapper,
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<PopularMovieEntity>> {
        return movieDao.getPopularMovies()
    }

    override suspend fun refreshPopularMovies() {
        service.getPopularMovies().takeIf { it.isSuccessful }
            ?.body()
            ?.results
            ?.filterNotNull()
            ?.let { movieDao.insertPopularMovies(it.map { item -> popularMovieMapper.map(item) }) }
    }

    override fun getNowPlayingMovies(): Flow<List<NowPlayingMovieEntity>> {
        return movieDao.getNowPlayingMovies()
    }

    override suspend fun refreshNowPlayingMovies() {
        service.getNowPlayingMovies().takeIf { it.isSuccessful }
            ?.body()
            ?.results
            ?.filterNotNull()
            ?.let { movieDao.insertNowPlayingMovies(it.map { item -> nowPlayingMovieMapper.map(item) }) }
    }

    override fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>> {
        return movieDao.getTopRatedMovies()
    }

    override suspend fun refreshTopRatedMovies() {
        service.getTopRatedMovies().takeIf { it.isSuccessful }
            ?.body()
            ?.results
            ?.filterNotNull()
            ?.let { movieDao.insertTopRatedMovies(it.map { item -> topRatedMovieMapper.map(item) }) }

    }

    override fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> {
        return movieDao.getUpcomingMovies()
    }

    override suspend fun refreshUpcomingMovies() {
        service.getUpcomingMovies().takeIf { it.isSuccessful }
            ?.body()
            ?.results
            ?.filterNotNull()
            ?.let { movieDao.insertUpcomingMovies(it.map { item -> upComingMovieMapper.map(item) }) }

    }


}