package com.chocolatecake.movieapp.data.repository

import com.chocolatecake.movieapp.data.local.database.MovieDao
import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.data.local.mappers.movie.LocalPopularMovieMapper
import com.chocolatecake.movieapp.data.remote.service.MovieService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val service: MovieService,
    private val movieDao: MovieDao,
    private val popularMovieMapper: LocalPopularMovieMapper,
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
        TODO("Not yet implemented")
    }

    override fun getTopRatedMovies(): Flow<List<TopRatedMovieEntity>> {
        TODO("Not yet implemented")
    }

    override fun getUpcomingMovies(): Flow<List<UpcomingMovieEntity>> {
        TODO("Not yet implemented")
    }
}