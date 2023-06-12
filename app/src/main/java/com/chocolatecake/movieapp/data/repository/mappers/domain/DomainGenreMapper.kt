package com.chocolatecake.movieapp.data.repository.mappers.domain

import com.chocolatecake.movieapp.data.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import com.chocolatecake.movieapp.domain.entities.GenreEntity
import javax.inject.Inject

class DomainGenreMapper @Inject constructor() : Mapper<GenresMoviesLocalDto, GenreEntity> {
    override fun map(input: GenresMoviesLocalDto): GenreEntity {
        return GenreEntity(
            genreID =  input.id,
            genreName = input.name
        )
    }
}