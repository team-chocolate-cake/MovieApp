package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import javax.inject.Inject

class PopularMoviesUiMapper @Inject constructor() : Mapper<PopularMovieLocalDto, MovieEntity> {
    override fun map(input: PopularMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            imageUrl = input.imageUrl,
            rate = input.rate,
        )
    }
}