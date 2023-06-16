package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.TvDetailsCreditEntity
import com.chocolatecake.remote.response.dto.TvDetailsCreditRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTvDetailsCreditMapper @Inject constructor() :
    Mapper<TvDetailsCreditRemoteDto, TvDetailsCreditEntity> {
    override fun map(input: TvDetailsCreditRemoteDto): TvDetailsCreditEntity {
        return TvDetailsCreditEntity(
            cast = input.cast?.map(::mapCastToEntity) ?: emptyList()
        )
    }

    private fun mapCastToEntity(castRemoteDto: TvDetailsCreditRemoteDto.Cast?)
            : TvDetailsCreditEntity.Cast {
        return TvDetailsCreditEntity.Cast(
            id = castRemoteDto?.id ?: 0,
            name = castRemoteDto?.name ?: "",
            originalName = castRemoteDto?.originalName ?: "",
            profileImageUrl = castRemoteDto?.profilePath ?: "",
            character = getActorRoles(castRemoteDto?.roles)?.character ?: ""
        )
    }

    /*
    If there are more than two roles, it means the actor has multiple characters
    The last role represents both characters, so we return the last role
    */
    private fun getActorRoles(roles: List<TvDetailsCreditRemoteDto.Role?>?)
            : TvDetailsCreditRemoteDto.Role? =
        roles?.let { if (it.size > 2) it.last() else it.firstOrNull() }

}
