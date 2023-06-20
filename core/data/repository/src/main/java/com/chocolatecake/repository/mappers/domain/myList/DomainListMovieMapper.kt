package com.chocolatecake.repository.mappers.domain.myList

import com.chocolatecake.entities.myList.ListEntity
import com.chocolatecake.entities.myList.ListMovieEntity
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.local.database.dto.myList.ListLocalDto
import com.chocolatecake.local.database.dto.myList.ListMovieLocalDto
import com.chocolatecake.remote.response.dto.ListRemoteDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainListMovieMapper @Inject constructor() : Mapper<ListMovieLocalDto, ListMovieEntity> {
    override fun map(input: ListMovieLocalDto): ListMovieEntity {
        return ListMovieEntity(
            mediaId = input.mediaId ?: 0,
            mediaType = input.mediaType ?: "",
            posterPath = input.posterPath ?: "",
            listId = input.listId ?: 0,
            nameList = input.nameList ?:"",
        )
    }
}