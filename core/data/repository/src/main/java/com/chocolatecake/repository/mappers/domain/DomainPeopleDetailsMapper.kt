package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.remote.response.dto.PeopleDetailsResponse
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleDetailsMapper @Inject constructor() :
    Mapper<PeopleDetailsResponse, PeopleDataEntity> {
    override fun map(input: PeopleDetailsResponse): PeopleDataEntity {
        return PeopleDataEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageUrl = (BuildConfig.IMAGE_BASE_PATH + input.profilePath) ?: "", placeOfBirth = input.placeOfBirth ?: "",
            gender = input.gender.toString(),
            acting = input.knownForDepartment.toString(), num_movies = "", biography = input.biography ?:""

        )
    }
}