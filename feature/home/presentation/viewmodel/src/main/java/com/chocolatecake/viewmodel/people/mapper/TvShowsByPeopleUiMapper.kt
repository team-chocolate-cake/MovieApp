package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import javax.inject.Inject

class TvShowsByPeopleUiMapper @Inject constructor() :  Mapper<TvShowEntity, PeopleDetailsUiState.PeopleMediaUiState> {
    override fun map(input: TvShowEntity): PeopleDetailsUiState.PeopleMediaUiState {
        return PeopleDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "tvShows",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}