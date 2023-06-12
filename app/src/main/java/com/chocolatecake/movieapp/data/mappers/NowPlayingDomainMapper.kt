package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.NowPlayingMovieLocalDto
import javax.inject.Inject

class NowPlayingDomainMapper @Inject constructor() : Mapper<NowPlayingMovieLocalDto, SimpleMovieEntity> {
    override fun map(input: NowPlayingMovieLocalDto): SimpleMovieEntity {
        return SimpleMovieEntity(
            id = input.id,
            imageUrl = input.imageUrl,
            rate = input.rate
        )
    }
}