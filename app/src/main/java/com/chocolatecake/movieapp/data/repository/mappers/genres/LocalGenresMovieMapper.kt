package com.chocolatecake.movieapp.data.repository.mappers.genres

import com.chocolatecake.movieapp.data.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import javax.inject.Inject

class LocalGenresMovieMapper @Inject constructor() : Mapper<GenreMovieDto, GenresMoviesLocalDto> {
    override fun map(input: GenreMovieDto): GenresMoviesLocalDto {
        return GenresMoviesLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}