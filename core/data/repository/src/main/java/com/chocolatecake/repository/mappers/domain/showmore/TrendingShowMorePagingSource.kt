package com.chocolatecake.repository.showmore

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.movie.DomainTrendingMovieMapperShowMore
import javax.inject.Inject

class TrendingShowMorePagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainTrendingMovieMapperShowMore
) : BasePagingSource<MovieEntity>(service) {

    override suspend fun fetchData(page: Int): List<MovieEntity> {

        val response = service.getTrendingMovies(page = page).body()?.results?.filterNotNull()

        return response?.map { mapper.map(it) } ?: emptyList()
    }
}