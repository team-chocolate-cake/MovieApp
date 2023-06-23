package com.chocolatecake.repository.mappers.cash

import com.chocolatecake.local.database.dto.GenresTvsLocalDto
import com.chocolatecake.remote.response.dto.GenreTVRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalGenresTvMapper @Inject constructor() :
    Mapper<GenreTVRemoteDto, GenresTvsLocalDto> {

    override fun map(input: GenreTVRemoteDto): GenresTvsLocalDto {
        return GenresTvsLocalDto(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}