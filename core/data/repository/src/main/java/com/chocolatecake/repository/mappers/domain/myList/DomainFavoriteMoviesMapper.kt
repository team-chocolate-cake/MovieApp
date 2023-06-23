package com.chocolatecake.repository.mappers.domain.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.dto.movie.MovieLocalDto
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainFavoriteMoviesMapper @Inject constructor()
    :Mapper<MovieLocalDto, MovieEntity> {
    override fun map(input: MovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            title = input.title ?:"",
            rate = input.rate ?:0.0,
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.imageUrl,
            year = input.year ?: "",
            genreEntities = emptyList(),
        )
    }
}