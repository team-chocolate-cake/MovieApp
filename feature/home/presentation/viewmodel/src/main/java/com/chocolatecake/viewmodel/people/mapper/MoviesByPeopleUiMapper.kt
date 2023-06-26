package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import javax.inject.Inject

class MoviesByPeopleUiMapper @Inject constructor() :
    Mapper<MovieEntity, PeopleDetailsUiState.PeopleMediaUiState> {
    override fun map(input: MovieEntity): PeopleDetailsUiState.PeopleMediaUiState {
        return PeopleDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "movies",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}