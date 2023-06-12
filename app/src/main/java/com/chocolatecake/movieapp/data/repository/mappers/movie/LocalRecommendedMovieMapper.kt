package com.chocolatecake.movieapp.data.repository.mappers.movie

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.dto.movie.RecommendedMovieLocalDto
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.mappers.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class LocalRecommendedMovieMapper@Inject constructor(): Mapper<MovieDto, RecommendedMovieLocalDto> {
    override fun map(input: MovieDto): RecommendedMovieLocalDto {
        return RecommendedMovieLocalDto(
            id = input.id ?: 0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}