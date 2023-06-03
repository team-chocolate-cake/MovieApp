package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalNowPlayingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalTopRatedMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalUpcomingMovieMapper
import com.chocolatecake.movieapp.data.local.mappers.people.LocalPopularPeopleMapper
import com.chocolatecake.movieapp.data.remote.service.MovieService
import com.chocolatecake.movieapp.data.repository.base.BaseRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val popularMovieMapper: LocalPopularMovieMapper,
    private val nowPlayingMovieMapper: LocalNowPlayingMovieMapper,
    private val topRatedMovieMapper: LocalTopRatedMovieMapper,
    private val upComingMovieMapper: LocalUpcomingMovieMapper,
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
}