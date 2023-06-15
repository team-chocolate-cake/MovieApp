package com.chocolatecake.viewmodel.people.mapper

import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import javax.inject.Inject

class PeopleDataUiMapper @Inject constructor():Mapper<PeopleDataEntity, PeopleDetailsUiState.PeopleDataUiState> {
    override fun map(input: PeopleDataEntity): PeopleDetailsUiState.PeopleDataUiState {
        return PeopleDetailsUiState.PeopleDataUiState(
            input.id,
            input.name,
            imageUrl = input.imageUrl,
            input.placeOfBirth,
            input.gender,
            input.biography
        )
    }
}