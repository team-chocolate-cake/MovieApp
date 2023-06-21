package com.chocolatecake.repository.showmore

import android.util.Log
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.movie.DomainPopularMovieMapper
import com.chocolatecake.repository.mappers.domain.movie.DomainPopularMovieMapperShowMore
import com.chocolatecake.repository.mappers.domain.movie.DomainTrendingMoviesMapper
import javax.inject.Inject

class PopularMoviesShowMorePagingSource  @Inject constructor(
    service: MovieService,
    private val mapper: DomainPopularMovieMapperShowMore
) : BasePagingSource<MovieEntity>(service) {

    override suspend fun fetchData(page: Int): List<MovieEntity> {

        val response = service.getPopularMovies(page).body()?.results?.filterNotNull()
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}