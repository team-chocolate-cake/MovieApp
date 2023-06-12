package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.PopularPeopleLocalDto
import javax.inject.Inject

class PopularPeopleUiMapper @Inject constructor() : Mapper<PopularPeopleLocalDto, SimpleMovieEntity> {
    override fun map(input: PopularPeopleLocalDto): SimpleMovieEntity {
        return SimpleMovieEntity(
            id = input.id,
            imageUrl = input.profilePath,
            title = input.name,
            rate = input.rate
        )
    }
}