package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.domain.model.movie.HomeMovie
import com.chocolatecake.movieapp.domain.model.movie.Movie
import javax.inject.Inject

class PopularMoviesUiMapper @Inject constructor() : Mapper<PopularMovieEntity, Movie> {
    override fun map(input: PopularMovieEntity): Movie {
        return Movie(
            id = input.id,
            imageUrl = input.imageUrl,
            voteAverage = input.rate,
        )
    }
}