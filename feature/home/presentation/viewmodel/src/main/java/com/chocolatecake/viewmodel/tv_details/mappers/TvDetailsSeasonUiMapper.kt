package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.SeasonHorizontalUIState
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsSeasonUiMapper @Inject constructor() : Mapper<List<SeasonEntity>, TvDetailsUiState> {
    override fun map(input: List<SeasonEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            seasons = input.map { season ->
                SeasonHorizontalUIState(
                    id = season.id,
                    imageUrl = season.imageUrl,
                    title = season.title,
                    description = season.description,
                    year = season.year,
                    countEpisode = season.countEpisode
                )
            }
        )
    }


}