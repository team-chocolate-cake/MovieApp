package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.UpcomingMovieLocalDto
import javax.inject.Inject

class UpComingUiMapper @Inject constructor() : Mapper<UpcomingMovieLocalDto, HomeMovie> {
    override fun map(input: UpcomingMovieLocalDto): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}