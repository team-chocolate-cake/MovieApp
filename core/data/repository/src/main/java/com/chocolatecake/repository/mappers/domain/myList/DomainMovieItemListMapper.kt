package com.chocolatecake.repository.mappers.domain.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.MovieItemListRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMovieItemListMapper @Inject constructor()
    :Mapper<MovieItemListRemoteDto, MovieEntity> {
    override fun map(input: MovieItemListRemoteDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.voteAverage ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
            year = input.releaseDate ?: "",
            genreEntities = emptyList(),
        )
    }
}