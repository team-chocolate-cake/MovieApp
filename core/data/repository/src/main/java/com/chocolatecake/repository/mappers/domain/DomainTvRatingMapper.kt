package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.remote.response.dto.TvRatingRemoteDto
import com.chocolatecake.repository.mappers.Mapper

class DomainTvRatingMapper() : Mapper<TvRatingRemoteDto, TvRatingEntity> {
    override fun map(input: TvRatingRemoteDto): TvRatingEntity {
        return TvRatingEntity(
            success = input.success ?: false,
            statusCode = input.statusCode ?: 0,
            statusMessage = input.statusMessage ?: ""
        )
    }
}
