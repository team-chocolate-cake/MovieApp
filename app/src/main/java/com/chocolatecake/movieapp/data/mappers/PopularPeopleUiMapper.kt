package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.ui.home.ui_state.PopularPeopleUiState
import javax.inject.Inject

class PopularPeopleUiMapper @Inject constructor() :
    Mapper<PopularPeopleEntity, PopularPeopleUiState> {
    override fun map(input: PopularPeopleEntity): PopularPeopleUiState {
        return PopularPeopleUiState(
            input.id,
            input.profilePath,
            input.name
        )
    }
}