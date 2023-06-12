package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainPeopleMapper @Inject constructor() : Mapper<PopularPeopleLocalDto, PeopleEntity> {
    override fun map(input: PopularPeopleLocalDto): PeopleEntity {
        return PeopleEntity(
            id =  input.id,
            name = input.name,
            imageUrl = input.imagerUrl
        )
    }
}