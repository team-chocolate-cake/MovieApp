package com.chocolatecake.repository.mappers.domain.episode

import com.chocolatecake.entities.EpisodeDetailsEntity
import com.chocolatecake.remote.response.dto.episode_details.EpisodeDetailsRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainEpisodeDetailsMapper @Inject constructor() :
    Mapper<EpisodeDetailsRemoteDto, EpisodeDetailsEntity> {
    override fun map(input: EpisodeDetailsRemoteDto): EpisodeDetailsEntity {
        return EpisodeDetailsEntity(
            id = input.id ?: 0,
            overview = input.overview ?: "",
            productionCode = input.productionCode ?: "",
            seasonNumber = input.seasonNumber ?: 0,
            episodeNumber = input.episodeNumber ?: 0,
            episodeName = input.name ?: "",
            voteAverage = input.voteAverage ?: 0f,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.stillPath,
            episodeRate = input.voteAverage ?: 0.0F
        )
    }

}