package com.chocolatecake.viewmodel.home.mappers

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.home.PopularPeopleUiState
import javax.inject.Inject

class PopularPeopleUiMapper @Inject constructor() :
    Mapper<PeopleEntity, PopularPeopleUiState> {
    override fun map(input: PeopleEntity): PopularPeopleUiState {
        return PopularPeopleUiState(
            input.id,
            input.imageUrl,
            input.name
        )
    }
}