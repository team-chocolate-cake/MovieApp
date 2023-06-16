package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvRatingUiMapper @Inject constructor():Mapper<TvRatingEntity,TvDetailsUiState> {
    override fun map(input: TvRatingEntity): TvDetailsUiState {
        return TvDetailsUiState(
            ratingSuccess = input.statusMessage
        )
    }

}