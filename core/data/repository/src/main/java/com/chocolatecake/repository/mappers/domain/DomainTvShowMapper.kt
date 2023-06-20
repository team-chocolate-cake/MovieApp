package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.remote.response.dto.TVShowsRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvShowMapper @Inject constructor() : Mapper<TVShowsRemoteDto, TvShowEntity> {

    override fun map(input: List<TVShowsRemoteDto>): List<TvShowEntity> {
        return input.map(::map)
    }

    override fun map(input: TVShowsRemoteDto): TvShowEntity {
        return TvShowEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = (BuildConfig.IMAGE_BASE_PATH + input.posterPath),
            rate = input.voteAverage?.times(0.5) ?: 0.0
        )
    }
}