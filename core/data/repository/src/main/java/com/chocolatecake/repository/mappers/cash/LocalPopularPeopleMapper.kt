package com.chocolatecake.repository.mappers.cash

import com.chocolatecake.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.remote.response.dto.PeopleRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalPopularPeopleMapper @Inject constructor() :
    Mapper<PeopleRemoteDto, PopularPeopleLocalDto> {
    override fun map(input: PeopleRemoteDto): PopularPeopleLocalDto {
        return PopularPeopleLocalDto(
            id = input.id ?: 0,
            imagerUrl = BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            name = input.name ?: "",
            popularity = input.popularity ?: 0.0
        )
    }
}