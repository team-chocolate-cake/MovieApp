package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsInfoUiMapper @Inject constructor() : Mapper<TvDetailsInfoEntity, TvDetailsUiState> {
    override fun map(input: TvDetailsInfoEntity): TvDetailsUiState {
        return TvDetailsUiState(
            info = TvDetailsUiState.Info(
                backdropImageUrl = input.backdropImageUrl,
                name = input.name,
                rating = input.rating,
                description = input.description,
                genres = mapGenereToUi(input.genres)
            ),
        )
    }


    private fun mapGenereToUi(genereEntities: List<GenreEntity>): List<String> {
        return genereEntities.map {
            it.genreName
        }
    }
}