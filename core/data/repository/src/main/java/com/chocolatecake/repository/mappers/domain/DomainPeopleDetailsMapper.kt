package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.remote.response.PeopleDetailsResponse
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleDetailsMapper @Inject constructor() :
    Mapper<PeopleDetailsResponse, PeopleDataEntity> {
    override fun map(input: PeopleDetailsResponse): PeopleDataEntity {
        return PeopleDataEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = input.profilePath ?: "", placeOfBirth = input.placeOfBirth ?: "",
            gender = input.gender ?:0,
            biography = input.biography ?:""

        )
    }
}