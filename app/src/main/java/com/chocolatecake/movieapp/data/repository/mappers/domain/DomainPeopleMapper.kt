package com.chocolatecake.movieapp.data.repository.mappers.domain

import com.chocolatecake.movieapp.data.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import com.chocolatecake.movieapp.domain.entities.PeopleEntity
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