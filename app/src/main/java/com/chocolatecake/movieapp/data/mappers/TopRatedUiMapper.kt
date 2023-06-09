package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import javax.inject.Inject

class TopRatedUiMapper @Inject constructor()  : Mapper<TopRatedMovieEntity, HomeMovie> {
    override fun map(input: TopRatedMovieEntity): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}