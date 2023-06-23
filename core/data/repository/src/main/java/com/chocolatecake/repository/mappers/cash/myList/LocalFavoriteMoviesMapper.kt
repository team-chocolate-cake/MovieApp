package com.chocolatecake.repository.mappers.cash.myList

import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalFavoriteMoviesMapper @Inject constructor()
    :Mapper<MovieRemoteDto, MovieLocalDto> {
    override fun map(input: MovieRemoteDto): MovieLocalDto {
        return MovieLocalDto(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.voteAverage ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
        )
    }
}