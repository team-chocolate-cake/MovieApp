package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.remote.response.dto.YoutubeVideoDetailsRemoteDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainYoutubeDetailsMapper @Inject constructor() :
    Mapper<YoutubeVideoDetailsRemoteDto, YoutubeVideoDetailsEntity> {
    override fun map(input: YoutubeVideoDetailsRemoteDto): YoutubeVideoDetailsEntity {
        return YoutubeVideoDetailsEntity(
            key = input.key ?: "",
            name = input.name ?: "",
            site = input.site ?: "",
            type = input.type ?: ""
        )
    }
}