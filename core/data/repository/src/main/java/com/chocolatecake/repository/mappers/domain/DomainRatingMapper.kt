package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.movieDetails.RatingResponseEntity
import com.chocolatecake.remote.response.movieDetails.RatingDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainRatingMapper @Inject constructor() : Mapper<RatingDto, RatingResponseEntity> {
    override fun map(input: RatingDto): RatingResponseEntity {
        return RatingResponseEntity(
           statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            success = input.success
        )
    }
}