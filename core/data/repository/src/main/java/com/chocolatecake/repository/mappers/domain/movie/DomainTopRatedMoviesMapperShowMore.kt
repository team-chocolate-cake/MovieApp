package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import javax.inject.Inject

class DomainTopRatedMoviesMapperShowMore @Inject constructor() {

    fun map(input: MovieRemoteDto, genreEntities: List<GenreEntity>): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
            genreEntities = genreEntities.filter {
                it.genreID in (input.genreIds?.filterNotNull() ?: emptyList())
            },
            rate = input.voteAverage ?: 0.0
        )
    }
}