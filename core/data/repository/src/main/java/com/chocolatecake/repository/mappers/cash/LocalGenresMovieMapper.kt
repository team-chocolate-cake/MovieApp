package com.chocolatecake.repository.mappers.cash

import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.remote.response.dto.GenreMovieRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalGenresMovieMapper @Inject constructor() :
    Mapper<GenreMovieRemoteDto, GenresMoviesLocalDto> {

    override fun map(input: GenreMovieRemoteDto): GenresMoviesLocalDto {
        return GenresMoviesLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}