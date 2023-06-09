package com.chocolatecake.movieapp.data.repository.mappers.movie

import com.chocolatecake.movieapp.data.local.database.entity.movie.MovieEntity
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalMovieMapper @Inject constructor(): Mapper<MovieDto, MovieEntity> {
    override fun map(input: MovieDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            voteAverage = input.voteAverage ?: 0.0,
            title = input.title,
            posterPath = input.posterPath,
            voteCount = input.voteCount,
        )
    }
}