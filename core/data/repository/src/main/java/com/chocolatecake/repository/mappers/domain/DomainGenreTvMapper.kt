package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.local.database.dto.GenresTvsLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainGenreTvMapper @Inject constructor() : Mapper<GenresTvsLocalDto, GenreEntity> {
    override fun map(input: GenresTvsLocalDto): GenreEntity {
        return GenreEntity(
            genreID =  input.id,
            genreName = input.name
        )
    }
}