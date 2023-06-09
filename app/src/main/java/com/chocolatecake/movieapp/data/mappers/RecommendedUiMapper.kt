package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import javax.inject.Inject

class RecommendedUiMapper @Inject constructor()  : Mapper<RecommendedMovieEntity, HomeMovie> {
    override fun map(input: RecommendedMovieEntity): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}