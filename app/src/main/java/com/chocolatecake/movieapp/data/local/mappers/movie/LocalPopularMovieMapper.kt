package com.chocolatecake.movieapp.data.local.mappers.movie

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalPopularMovieMapper @Inject constructor(): Mapper<MovieDto, PopularMovieEntity> {
    override fun map(input: MovieDto): PopularMovieEntity {
        return PopularMovieEntity(
            id = input.id ?: 0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}