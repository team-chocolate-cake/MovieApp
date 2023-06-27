package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.PeopleDetailsEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.people.PersonDetailsUiState
import javax.inject.Inject

class PeopleDataUiMapper @Inject constructor() :
    Mapper<PeopleDetailsEntity, PersonDetailsUiState.PersonInfoUiState> {
    override fun map(input: PeopleDetailsEntity): PersonDetailsUiState.PersonInfoUiState {
        return PersonDetailsUiState.PersonInfoUiState(
            input.id,
            input.name,
            imageUrl = input.imageUrl,
            input.placeOfBirth,
            input.gender,
            acting = input.acting,
            num_movies = input.num_movies,
            input.biography,

            )
    }
}