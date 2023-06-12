package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.TrendingMoviesLocalDto
import javax.inject.Inject

class TrendingUiMapper @Inject constructor()  : Mapper<TrendingMoviesLocalDto, HomeMovie> {
    override fun map(input: TrendingMoviesLocalDto): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}