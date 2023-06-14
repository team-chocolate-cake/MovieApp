package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainTopRatedMovieMapper @Inject constructor() : Mapper<TopRatedMovieLocalDto, MovieEntity> {

    override fun map(input: TopRatedMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}