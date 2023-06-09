package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import javax.inject.Inject

class TrendingUiMapper @Inject constructor()  : Mapper<TrendingMoviesEntity, HomeMovie> {
    override fun map(input: TrendingMoviesEntity): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}