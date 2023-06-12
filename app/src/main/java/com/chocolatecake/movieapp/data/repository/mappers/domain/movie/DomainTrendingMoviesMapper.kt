package com.chocolatecake.movieapp.data.repository.mappers.domain.movie

import com.chocolatecake.movieapp.data.local.database.dto.movie.TrendingMoviesLocalDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import javax.inject.Inject


class DomainTrendingMoviesMapper @Inject constructor() : Mapper<TrendingMoviesLocalDto, MovieEntity> {

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