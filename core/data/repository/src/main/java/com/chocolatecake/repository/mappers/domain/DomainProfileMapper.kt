package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.local.database.dto.ProfileLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainProfileMapper @Inject constructor(): Mapper<ProfileLocalDto, ProfileEntity> {
    override fun map(input: ProfileLocalDto): ProfileEntity {
        return ProfileEntity(
            username = input.username,
            avatarUrl = input.avatarUrl
        )
    }
}