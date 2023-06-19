package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainGenreMapper @Inject constructor() : Mapper<GenresMoviesLocalDto, GenreEntity> {
    override fun map(input: GenresMoviesLocalDto): GenreEntity {
        return GenreEntity(
            genreID =  input.id,
            genreName = input.name
        )
    }
}