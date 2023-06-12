package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.domain.entities.GenreEntity
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreMovieDto, GenreEntity> {
    override fun map(input: GenreMovieDto): GenreEntity {
        return GenreEntity(
            genreID = input.id ?: 0,
            genreName = input.name ?: ""
        )
    }
}