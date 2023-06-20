package com.chocolatecake.repository.mappers.domain.episode

import com.chocolatecake.entities.CastEpisodeDetailsEntity
import com.chocolatecake.remote.response.dto.episode_details.CastRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainCastMapper @Inject constructor() :
    Mapper<CastRemoteDto, CastEpisodeDetailsEntity> {

    override fun map(input: CastRemoteDto): CastEpisodeDetailsEntity {
        return CastEpisodeDetailsEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            profilePath = input.profilePath ?: ""
        )
    }
}