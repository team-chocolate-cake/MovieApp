package com.chocolatecake.repository.my_rated

import com.chocolatecake.entities.my_rated.MyRatedMovieEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.my_rated.DomainMyRatedMoviesMapper
import javax.inject.Inject


class RatedMoviesPagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainMyRatedMoviesMapper
) : BasePagingSource<MyRatedMovieEntity>(service) {
    override suspend fun fetchData(page: Int): List<MyRatedMovieEntity> {
        val response = service.getRatedMovies(page).body()?.movies?.filterNotNull()
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}