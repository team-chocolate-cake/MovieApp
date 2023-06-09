package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.domain.model.movie.HomePopularMovie
import javax.inject.Inject

class PopularPeopleUiMapper @Inject constructor() : Mapper<PopularPeopleEntity, HomePopularMovie> {
    override fun map(input: PopularPeopleEntity): HomePopularMovie {
        return HomePopularMovie(
            input.id,
            input.profilePath,
            input.name
        )
    }
}