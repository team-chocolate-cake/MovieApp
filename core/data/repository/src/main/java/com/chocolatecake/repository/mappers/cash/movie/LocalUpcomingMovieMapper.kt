package com.chocolatecake.repository.mappers.cash.movie

import com.chocolatecake.local.database.dto.movie.UpcomingMovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalUpcomingMovieMapper @Inject constructor():
    Mapper<MovieRemoteDto, UpcomingMovieLocalDto> {
    override fun map(input: MovieRemoteDto): UpcomingMovieLocalDto {
        return UpcomingMovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}