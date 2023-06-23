package com.chocolatecake.repository.mappers.cash.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.dto.movie.MovieListDetailsLocalDto
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class LocalMoviesMapper @Inject constructor()
    :Mapper<MovieRemoteDto, MovieListDetailsLocalDto> {
    override fun map(input: MovieRemoteDto): MovieListDetailsLocalDto {
        return MovieListDetailsLocalDto(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.voteAverage ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
        )
    }
}