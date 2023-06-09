package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import javax.inject.Inject

class NowPlayingDomainMapper @Inject constructor() : Mapper<NowPlayingMovieEntity, HomeMovie> {
    override fun map(input: NowPlayingMovieEntity): HomeMovie {
        return HomeMovie(
            id = input.id,
            imageUrl = input.imageUrl,
            voteAverage = input.rate
        )
    }
}