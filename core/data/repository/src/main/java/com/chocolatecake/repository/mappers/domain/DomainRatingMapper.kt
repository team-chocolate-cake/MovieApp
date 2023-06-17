package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.movieDetails.RatingEntity
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.remote.response.movieDetails.RatingDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainRatingMapper @Inject constructor() : Mapper<RatingDto, RatingEntity> {
    override fun map(input: RatingDto): RatingEntity {
        return RatingEntity(
           statusCode = input.statusCode,
            statusMessage = input.statusMessage,
            success = input.success
        )
    }
}