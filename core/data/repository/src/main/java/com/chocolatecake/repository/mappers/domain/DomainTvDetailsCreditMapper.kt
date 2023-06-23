package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.remote.response.dto.TvDetailsCreditRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvDetailsCreditMapper @Inject constructor() :
    Mapper<TvDetailsCreditRemoteDto, List<PeopleEntity>> {
    override fun map(input: TvDetailsCreditRemoteDto): List<PeopleEntity> {
        return mapCastToEntity(input.cast)
    }

    private fun mapCastToEntity(castRemoteDto: List<TvDetailsCreditRemoteDto.Cast?>?)
            : List<PeopleEntity> {
        return castRemoteDto?.map { actor ->
            PeopleEntity(
                id = actor?.id ?: 0,
                name = actor?.name ?: "",
                imageUrl = (BuildConfig.IMAGE_BASE_PATH + actor?.profilePath) ?: ""
            )
        } ?: emptyList()
    }

}
