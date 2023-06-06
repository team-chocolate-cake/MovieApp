package com.chocolatecake.movieapp.data.local.mappers.people

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.remote.response.actor.ActorDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalPopularPeopleMapper @Inject constructor() : Mapper<ActorDto, PopularPeopleEntity> {
    override fun map(input: ActorDto): PopularPeopleEntity {
        return PopularPeopleEntity(
            id = input.id ?: 0,
            profilePath = BuildConfig.IMAGE_BASE_PATH + input.profilePath,
            name = input.name ?: " "
        )
    }
}