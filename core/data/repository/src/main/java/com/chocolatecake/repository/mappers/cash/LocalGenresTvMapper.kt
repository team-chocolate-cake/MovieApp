package com.chocolatecake.repository.mappers.cash

import com.chocolatecake.local.database.dto.GenresTvsLocalDto
import com.chocolatecake.remote.response.dto.GenreTvRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalGenresTvMapper @Inject constructor() :
    Mapper<GenreTvRemoteDto, GenresTvsLocalDto> {

    override fun map(input: GenreTvRemoteDto): GenresTvsLocalDto {
        return GenresTvsLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}