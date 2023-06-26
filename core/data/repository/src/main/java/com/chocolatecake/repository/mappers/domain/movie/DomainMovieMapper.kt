package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieMapper @Inject constructor() {
     fun map(input: MovieRemoteDto , genres: List<GenreEntity>): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.voteAverage ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
            genreEntities = genres,
        )
    }
}