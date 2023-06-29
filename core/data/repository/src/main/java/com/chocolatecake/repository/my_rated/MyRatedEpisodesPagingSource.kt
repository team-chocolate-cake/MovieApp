package com.chocolatecake.repository.my_rated

import com.chocolatecake.entities.my_rated.MyRatedEpisodesEntity
import com.chocolatecake.remote.service.MovieService
import com.chocolatecake.repository.BasePagingSource
import com.chocolatecake.repository.mappers.domain.my_rated.MyRatedEpisodesDomainMapper
import javax.inject.Inject

class MyRatedEpisodesPagingSource @Inject constructor(
    service: MovieService,
    private val mapper: MyRatedEpisodesDomainMapper
) : BasePagingSource<MyRatedEpisodesEntity>(service) {

    override suspend fun fetchData(page: Int): List<MyRatedEpisodesEntity> {

        val response = service.getAllMyRatedEpisodes(page).body()?.results?.filterNotNull()
        return response?.map { mapper.map(it) } ?: emptyList()
    }
}