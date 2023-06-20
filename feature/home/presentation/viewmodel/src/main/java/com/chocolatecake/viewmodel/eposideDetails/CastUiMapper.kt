package com.chocolatecake.viewmodel.eposideDetails

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class CastUiMapper @Inject constructor() : Mapper<PeopleEntity, PeopleUIState> {
    override fun map(input: PeopleEntity): PeopleUIState {
        return PeopleUIState(
            id = input.id,
            name = input.name,
            imageUrl = input.imageUrl
        )
    }
}