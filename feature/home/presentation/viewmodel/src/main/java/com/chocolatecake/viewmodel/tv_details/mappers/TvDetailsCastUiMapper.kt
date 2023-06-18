package com.chocolatecake.viewmodel.tv_details.mappers

import com.chocolatecake.entities.CastEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.tv_details.TvDetailsUiState
import javax.inject.Inject

class TvDetailsCastUiMapper @Inject constructor() :
    Mapper<List<PeopleEntity>, TvDetailsUiState> {

    override fun map(input: List<PeopleEntity>): TvDetailsUiState {
        return TvDetailsUiState(
            cast = mapCastToUi(input)
        )
    }

    private fun mapCastToUi(castEntity: List<PeopleEntity>): List<PeopleUIState> {
        return castEntity.map {
            PeopleUIState(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl
            )
        }
    }


}