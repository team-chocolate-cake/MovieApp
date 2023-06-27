package com.chocolatecake.repository.mappers.domain.showmore
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.MovieDao
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.DomainGenreMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainTopRatedMoviesMapperShowMore
import javax.inject.Inject

class TopRatedShowMorePagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainTopRatedMoviesMapperShowMore,
    private val domainGenreMapper: DomainGenreMapper,
    private val movieDao: MovieDao,
) : BasePagingSource<MovieEntity>(service) {
    override suspend fun fetchData(page: Int): List<MovieEntity> {
        val response = service.getTopRatedMovies(page).body()?.results?.filterNotNull()
        val genreMovieMapper = domainGenreMapper.map(movieDao.getGenresMovies())
        return response?.map { mapper.map(it, genreMovieMapper) } ?: emptyList()
    }
}