package com.chocolatecake.repository.mappers.domain.myList

import com.chocolatecake.entities.myList.ListEntity
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.local.database.dto.myList.ListLocalDto
import com.chocolatecake.remote.response.dto.ListRemoteDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainListMapper @Inject constructor() : Mapper<ListLocalDto, ListEntity> {
    override fun map(input: ListLocalDto): ListEntity {
        return ListEntity(
            itemCount = input.itemCount ?: 0,
            listType = input.listType ?: "",
            name = input.name ?: "",
        )
    }
}