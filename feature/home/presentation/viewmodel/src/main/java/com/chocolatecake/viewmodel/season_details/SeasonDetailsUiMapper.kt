package com.chocolatecake.viewmodel.season_details

import com.chocolatecake.entities.season_details.EpisodeEntity
import com.chocolatecake.entities.season_details.SeasonDetailsEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.EpisodeHorizontalUIState
import javax.inject.Inject

class SeasonDetailsUiMapper @Inject constructor():
    Mapper<SeasonDetailsEntity, SeasonDetailsUiState> {

    override fun map(input: SeasonDetailsEntity): SeasonDetailsUiState {
        return SeasonDetailsUiState(
            name = input.name,
            overview = input.overview,
            id = input.id,
            episodes = mapEpisodes(input.episodes),
            onErrors = emptyList(),
            isLoading = false
        )
    }

    private fun mapEpisodes(input: List<EpisodeEntity>): List<EpisodeHorizontalUIState>{
        return input.map {
            EpisodeHorizontalUIState(
                id = it.id,
                imageUrl = it.imageUrl,
                title = it.title,
                timeEpisode = it.timeEpisode,
                rate = it.rate,
                Description = it.overview,
                numberEpisode = it.episodeNumber
            )
        }
    }
}