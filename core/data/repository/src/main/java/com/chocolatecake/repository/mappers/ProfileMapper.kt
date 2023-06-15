package com.chocolatecake.repository.mappers

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.remote.response.dto.profile.ProfileRemoteDto
import javax.inject.Inject

class ProfileMapper @Inject constructor() : Mapper<ProfileRemoteDto, ProfileEntity> {
    override fun map(input: ProfileRemoteDto): ProfileEntity {
        return ProfileEntity(
            username = input.username,
            avatarUrl = input.avatar.tmdb.avatarPath
        )
    }
}