package com.chocolatecake.repository.mappers.domain.tv

import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.remote.response.dto.TVShowsRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class TVShowsDomainMapper @Inject constructor() :
    Mapper<TVShowsRemoteDto, TVShowsEntity> {

    override fun map(input: TVShowsRemoteDto): TVShowsEntity {
        return TVShowsEntity(
            id = input.id ?: 0,
            title = input.name ?: "",
            imageUrl = input.posterPath ?: "",
            genreEntities = emptyList(),
            rate = input.voteAverage ?: 0.0
        )
    }
}