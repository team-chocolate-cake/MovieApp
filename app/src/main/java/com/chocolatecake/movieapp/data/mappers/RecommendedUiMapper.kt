package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.RecommendedMovieLocalDto
import javax.inject.Inject

class RecommendedUiMapper @Inject constructor()  : Mapper<RecommendedMovieLocalDto, HomeMovie> {
    override fun map(input: RecommendedMovieLocalDto): HomeMovie {
        return HomeMovie(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}