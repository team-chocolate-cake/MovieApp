package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.people.PersonDetailsUiState
import javax.inject.Inject

class MoviesByPeopleUiMapper @Inject constructor() :
    Mapper<MovieEntity, PersonDetailsUiState.PeopleMediaUiState> {
    override fun map(input: MovieEntity): PersonDetailsUiState.PeopleMediaUiState {
        return PersonDetailsUiState.PeopleMediaUiState(
            id = input.id,
            name = "movies",
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}