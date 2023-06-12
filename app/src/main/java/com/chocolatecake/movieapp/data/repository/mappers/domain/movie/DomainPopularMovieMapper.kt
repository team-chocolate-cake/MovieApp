package com.chocolatecake.movieapp.data.repository.mappers.domain.movie

import com.chocolatecake.movieapp.data.local.database.dto.movie.PopularMovieLocalDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import javax.inject.Inject

class DomainPopularMovieMapper @Inject constructor():
    Mapper<PopularMovieLocalDto, MovieEntity> {

    override fun map(input: PopularMovieLocalDto): MovieEntity {
        return MovieEntity(
            id = input.id,
            title = input.title,
            imageUrl = input.imageUrl,
            genreEntities = emptyList(),
            rate = input.rate
        )
    }
}