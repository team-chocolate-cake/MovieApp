package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.people.PersonDetailsUiState
import javax.inject.Inject

class TvShowsByPeopleUiMapper @Inject constructor() :  Mapper<TvShowEntity, PersonDetailsUiState.PeopleMediaUiState> {
    override fun map(input: TvShowEntity): PersonDetailsUiState.PeopleMediaUiState {
        return PersonDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "tvShows",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}