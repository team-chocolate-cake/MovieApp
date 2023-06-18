package com.chocolatecake.repository.tv_shows

import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.tv.TVShowsDomainMapper
import javax.inject.Inject

class AiringTodayTVShowsPagingSource @Inject constructor(
    service: MovieService,
    private val mapper: TVShowsDomainMapper
) : BasePagingSource<TVShowsEntity>(service) {
    override suspend fun fetchData(page: Int): List<TVShowsEntity> {

        val response = service.getAiringTodayTVShows(page).body()?.results?.filterNotNull()
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}