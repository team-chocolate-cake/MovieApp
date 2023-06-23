package com.chocolatecake.repository.mappers.domain

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.remote.response.dto.StatusResponse
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainStatusMapper @Inject constructor() : Mapper<StatusResponse, StatusEntity> {
    override fun map(input: StatusResponse): StatusEntity {
        return StatusEntity(
            statusCode = input.statusCode?:0,
            statusMessage = input.statusMessage?:"",
            success = input.success?:false
        )
    }
}