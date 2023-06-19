package com.chocolatecake.repository.mappers.cash.movie

import com.chocolatecake.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalPopularMovieMapper @Inject constructor() :
    Mapper<MovieRemoteDto, PopularMovieLocalDto> {
    override fun map(input: MovieRemoteDto): PopularMovieLocalDto {
        return PopularMovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}