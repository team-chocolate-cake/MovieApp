package com.chocolatecake.viewmodel.eposideDetails

import com.chocolatecake.entities.CastEpisodeDetailsEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class CastUiMapper @Inject constructor() : Mapper<CastEpisodeDetailsEntity, PeopleUIState> {
    override fun map(input: CastEpisodeDetailsEntity): PeopleUIState {
        return PeopleUIState(
            id = input.id,
            name = input.name,
            imageUrl = input.profilePath
        )
    }
}