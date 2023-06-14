package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.local.database.dto.movie.TrendingMoviesLocalDto
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject


class DomainTrendingMoviesMapper @Inject constructor() :
    Mapper<TrendingMoviesLocalDto, MovieEntity> {

    override fun map(input: TrendingMoviesLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}