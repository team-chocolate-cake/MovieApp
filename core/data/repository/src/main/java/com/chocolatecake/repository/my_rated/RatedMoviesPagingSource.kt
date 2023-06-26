package com.chocolatecake.repository.my_rated


import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedMoviesMapper
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedTvShowMapper
import javax.inject.Inject

class RatedMoviesPagingSource @Inject constructor(
    service: MovieService,
    private val domainGenreMapper: DomainGenreMapper,
    private val mapper: DomainMyRatedMoviesMapper,
    private val movieDao: MovieDao,
) : BasePagingSource<MyRatedMovieEntity>(service) {
    override suspend fun fetchData(page: Int): List<MyRatedMovieEntity> {
        val response = service.getRatedMovies(page).body()?.movies?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        return response?.map { mapper.map(it , genreMovieMapper) } ?: emptyList()
    }

}