package com.chocolatecake.movieapp.data.repository.mappers.movie

import com.chocolatecake.movieapp.data.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalMovieMapper @Inject constructor(): Mapper<MovieDto, MovieLocalDto> {
    override fun map(input: MovieDto): MovieLocalDto {
        return MovieLocalDto(
            id = input.id ?: 0,
            rate = input.voteAverage ?: 0.0,
            title = input.title,
            posterPath = input.posterPath,
            voteCount = input.voteCount,
        )
    }
}