package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvRatingUiMapper @Inject constructor() : Mapper<StatusEntity, TvDetailsUiState> {
    override fun map(input: StatusEntity): TvDetailsUiState {
        return TvDetailsUiState(
            ratingSuccess = input.statusMessage
        )
    }

}