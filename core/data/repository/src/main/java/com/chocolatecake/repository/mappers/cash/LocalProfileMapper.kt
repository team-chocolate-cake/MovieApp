package com.chocolatecake.repository.mappers.cash

import com.chocolatecake.local.database.dto.ProfileLocalDto
import com.chocolatecake.remote.response.dto.profile.ProfileRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalProfileMapper @Inject constructor() :
    Mapper<ProfileRemoteDto, ProfileLocalDto> {
    override fun map(input: ProfileRemoteDto): ProfileLocalDto {
        return ProfileLocalDto(
            username = input.username ?: "",
            avatarUrl = BuildConfig.IMAGE_BASE_PATH + input.avatar?.tmdb?.avatarPath
        )
    }
}