package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.remote.response.dto.PeopleRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleRemoteMapper @Inject constructor() : Mapper<PeopleRemoteDto, PeopleEntity> {
    override fun map(input: PeopleRemoteDto): PeopleEntity {
        return PeopleEntity(
            id =  input.id ?: 0,
            name = input.name ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.profilePath
        )
    }
}