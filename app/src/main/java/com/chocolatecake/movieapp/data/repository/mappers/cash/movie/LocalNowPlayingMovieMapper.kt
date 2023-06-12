package com.chocolatecake.movieapp.data.repository.mappers.cash.movie

import com.chocolatecake.movieapp.BuildConfig
import com.chocolatecake.movieapp.data.local.database.dto.movie.NowPlayingMovieLocalDto
import com.chocolatecake.movieapp.data.remote.response.dto.MovieRemoteDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import javax.inject.Inject

class LocalNowPlayingMovieMapper @Inject constructor() :
    Mapper<MovieRemoteDto, NowPlayingMovieLocalDto> {
    override fun map(input: MovieRemoteDto): NowPlayingMovieLocalDto {
        return NowPlayingMovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            rate = input.voteAverage ?: 0.0
        )
    }
}