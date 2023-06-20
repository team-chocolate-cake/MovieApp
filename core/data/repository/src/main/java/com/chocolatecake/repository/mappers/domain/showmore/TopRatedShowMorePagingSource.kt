package com.chocolatecake.repository.showmore

import android.util.Log
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.movie.DomainTopRatedMapperShowMore
import com.chocolatecake.repository.mappers.domain.movie.DomainTopRatedMovieMapper
import javax.inject.Inject

class TopRatedShowMorePagingSource @Inject constructor(
    service: MovieService,
    private val mapper: DomainTopRatedMapperShowMore
) : BasePagingSource<MovieEntity>(service) {

    override suspend fun fetchData(page: Int): List<MovieEntity> {

        val response = service.getTopRatedMovies(page).body()?.results?.filterNotNull()
        val topRatedEntities = response?.map { mapper.map(it) } ?: emptyList()

        val test = service.getTopRatedMovies(page).isSuccessful
        Log.d("source--TopRated", "$test")

        return topRatedEntities
    }
}