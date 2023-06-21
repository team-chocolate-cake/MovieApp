package com.chocolatecake.repository.showmore

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.movie.DomainTopRatedMapperShowMore
import javax.inject.Inject

class TopRatedShowMorePagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainTopRatedMapperShowMore
) : BasePagingSource<MovieEntity>(service) {

    override suspend fun fetchData(page: Int): List<MovieEntity> {

        val response = service.getTopRatedMovies(page).body()?.results?.filterNotNull()


        return response?.map { mapper.map(it) } ?: emptyList()
    }
}