package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvShowUiMapper @Inject constructor() : Mapper<List<TvShowEntity>, TvDetailsUiState> {
    override fun map(input: List<TvShowEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            recommended = input.map { tvShow ->
                MediaVerticalUIState(
                    id = tvShow.id,
                    imageUrl = tvShow.imageUrl,
                    rate = tvShow.rate
                )
            }
        )
    }
}