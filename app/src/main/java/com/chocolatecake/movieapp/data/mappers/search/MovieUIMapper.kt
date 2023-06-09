package com.chocolatecake.movieapp.data.mappers.search

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.movie.Movie
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class MovieUIMapper @Inject constructor(): Mapper<MovieDto, Movie> {
    override fun map(input: MovieDto): Movie {
        return Movie(
            id = input.id ?: 0,
            voteAverage = input.voteAverage ?: 0.0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }
}