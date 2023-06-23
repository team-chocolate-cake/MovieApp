package com.chocolatecake.repository.mappers.domain.episode

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.remote.response.dto.episode_details.EpisodeDetailsCastRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainCastMapper @Inject constructor() :
    Mapper<EpisodeDetailsCastRemoteDto, List<PeopleEntity>> {
    override fun map(input: EpisodeDetailsCastRemoteDto): List<PeopleEntity> {
        return mapCastToEntity(input.cast)
    }


    private fun mapCastToEntity(castRemoteDto: List<EpisodeDetailsCastRemoteDto.CastRemoteDto>?)
            : List<PeopleEntity> {
        return castRemoteDto?.map { actor ->
            PeopleEntity(
                id = actor.id ?: 0,
                name = actor.name ?: "",
                imageUrl = (BuildConfig.IMAGE_BASE_PATH + actor.profilePath) ?: ""
            )
        } ?: emptyList()
    }
}