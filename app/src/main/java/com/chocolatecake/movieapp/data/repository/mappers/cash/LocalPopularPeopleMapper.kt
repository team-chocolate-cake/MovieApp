package com.chocolatecake.movieapp.data.repository.mappers.cash

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.movieapp.data.remote.response.dto.PeopleRemoteDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import javax.inject.Inject

class LocalPopularPeopleMapper @Inject constructor() : Mapper<PeopleRemoteDto, PopularPeopleLocalDto> {
    override fun map(input: PeopleRemoteDto): PopularPeopleLocalDto {
        return PopularPeopleLocalDto(
            id = input.id ?: 0,
            imagerUrl = BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            name = input.name ?: ""
        )
    }
}