package com.chocolatecake.movieapp.data.repository.mappers.people

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.dto.PopularPeopleLocalDto
import com.chocolatecake.movieapp.data.remote.response.actor.ActorDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalPopularPeopleMapper @Inject constructor() : Mapper<ActorDto, PopularPeopleLocalDto> {
    override fun map(input: ActorDto): PopularPeopleLocalDto {
        return PopularPeopleLocalDto(
            id = input.id ?: 0,
            profilePath = BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            name = input.name ?: " "
        )
    }
}