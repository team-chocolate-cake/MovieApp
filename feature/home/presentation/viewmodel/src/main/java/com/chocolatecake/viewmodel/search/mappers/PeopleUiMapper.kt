package com.chocolatecake.viewmodel.search.mappers

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import javax.inject.Inject

class PeopleUiMapper @Inject constructor() :
    Mapper<PeopleEntity, PeopleUIState> {
    override fun map(input: PeopleEntity): PeopleUIState {
        return PeopleUIState(
            input.id,
            input.name,
            input.imageUrl
        )
    }
}