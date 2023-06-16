package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsInfoUiMapper @Inject constructor() : Mapper<TvDetailsInfoEntity, TvDetailsUiState> {
    override fun map(input: TvDetailsInfoEntity): TvDetailsUiState {
        return TvDetailsUiState(
            backdropImageUrl = input.backdropImageUrl,
            name = input.name,
            rating = input.rating,
            description = input.description,
            seasons = input.seasons.map(::mapSeasonToUi),
            genres = mapGenereToUi(input.genres)
        )
    }

    private fun mapSeasonToUi(seasonEntity: TvDetailsInfoEntity.Season?)
            : TvDetailsUiState.Season {
        return TvDetailsUiState.Season(
            number = seasonEntity?.number ?: 0,
            episodeCount = seasonEntity?.episodeCount ?: 0,
            airDate = seasonEntity?.airDate ?: ""
        )
    }

    private fun mapGenereToUi(genereEntities: List<GenreEntity>): List<String> {
        return genereEntities.map {
            it.genreName
        }
    }
}