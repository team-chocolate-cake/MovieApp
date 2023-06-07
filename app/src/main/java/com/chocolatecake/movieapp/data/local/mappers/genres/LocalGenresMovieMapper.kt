package com.chocolatecake.movieapp.data.local.mappers.genres

import com.chocolatecake.movieapp.data.local.database.entity.GenresMoviesEntity
import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import javax.inject.Inject

class LocalGenresMovieMapper @Inject constructor() : Mapper<GenreMovieDto, GenresMoviesEntity> {
    override fun map(input: GenreMovieDto): GenresMoviesEntity {
        return GenresMoviesEntity(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}