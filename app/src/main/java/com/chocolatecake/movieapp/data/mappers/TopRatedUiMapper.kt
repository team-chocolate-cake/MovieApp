package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.TopRatedMovieLocalDto
import javax.inject.Inject

class TopRatedUiMapper @Inject constructor()  : Mapper<TopRatedMovieLocalDto, HomeMovie> {
    override fun map(input: TopRatedMovieLocalDto): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}