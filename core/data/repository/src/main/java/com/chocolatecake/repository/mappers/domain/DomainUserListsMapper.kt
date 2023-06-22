package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.remote.response.dto.UserListRemoteDto
import com.chocolatecake.repository.mappers.Mapper

class DomainUserListsMapper : Mapper<UserListRemoteDto, UserListEntity> {
    override fun map(input: UserListRemoteDto): UserListEntity {
        return UserListEntity(
            id = input.id ?: 0,
            name = input.name ?: ""
        )
    }
}