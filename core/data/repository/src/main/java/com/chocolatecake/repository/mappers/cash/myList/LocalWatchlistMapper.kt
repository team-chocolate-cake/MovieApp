package com.chocolatecake.repository.mappers.cash.myList

import com.chocolatecake.local.database.dto.movie.WatchlistLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalWatchlistMapper @Inject constructor()
    :Mapper<MovieRemoteDto, WatchlistLocalDto> {
    override fun map(input: MovieRemoteDto): WatchlistLocalDto {
        return WatchlistLocalDto(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.voteAverage ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
            mediaType = "movie",
        )
    }
}