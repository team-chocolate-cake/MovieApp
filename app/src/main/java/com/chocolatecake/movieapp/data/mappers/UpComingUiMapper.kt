package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import javax.inject.Inject

class UpComingUiMapper @Inject constructor() : Mapper<UpcomingMovieEntity, HomeMovie> {
    override fun map(input: UpcomingMovieEntity): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}