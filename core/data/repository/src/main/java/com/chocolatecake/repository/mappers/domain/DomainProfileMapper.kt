package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.remote.response.dto.profile.ProfileRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainProfileMapper @Inject constructor(): Mapper<ProfileRemoteDto, ProfileEntity> {
    override fun map(input: ProfileRemoteDto): ProfileEntity {
        return ProfileEntity(
            username = "@" + input.username,
            avatarUrl = BuildConfig.IMAGE_BASE_PATH + input.avatar?.tmdb?.avatarPath
        )
    }
}