package com.chocolatecake.repository.mappers.domain.episode

import com.chocolatecake.entities.RatingEpisodeDetailsEntity
import com.chocolatecake.remote.response.dto.episode_details.RatingEpisodeDetailsRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainRatingEpisodeMapper @Inject constructor() :
    Mapper<RatingEpisodeDetailsRemoteDto, RatingEpisodeDetailsEntity> {

    override fun map(input: RatingEpisodeDetailsRemoteDto): RatingEpisodeDetailsEntity {
        return RatingEpisodeDetailsEntity(
            averageRating = input.averageRating,
            voteCount = input.voteCount
        )
    }
}