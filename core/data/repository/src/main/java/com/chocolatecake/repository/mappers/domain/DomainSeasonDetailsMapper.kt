package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.season_details.EpisodeEntity
import com.chocolatecake.entities.season_details.SeasonDetailsEntity
import com.chocolatecake.remote.response.dto.season_details.EpisodeDto
import com.chocolatecake.remote.response.dto.season_details.SeasonDetailsDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainSeasonDetailsMapper @Inject constructor() :
    Mapper<SeasonDetailsDto, SeasonDetailsEntity> {

    override fun map(input: SeasonDetailsDto): SeasonDetailsEntity {
        return SeasonDetailsEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            overview = input.overview ?: "",
            episodes = mapEpisodes(input.episodes ?: emptyList())
        )
    }

    private fun mapEpisodes(input: List<EpisodeDto>): List<EpisodeEntity>{
        return input.map {
            EpisodeEntity(
                id = it.id ?: 0,
                imageUrl = BuildConfig.IMAGE_BASE_PATH + it.stillPath ,
                title = it.name ?: "",
                overview = it.overview ?: "",
                timeEpisode = it.runtime ?: 0,
                rate = it.voteAverage ?: 0.0,
                episodeNumber = it.episodeNumber ?: 0
            )
        }
    }
}