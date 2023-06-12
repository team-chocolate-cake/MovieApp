package com.chocolatecake.movieapp.data.repository.mappers.domain.movie

import com.chocolatecake.movieapp.data.local.database.dto.movie.TopRatedMovieLocalDto
import com.chocolatecake.movieapp.data.repository.mappers.Mapper
import com.chocolatecake.movieapp.domain.entities.MovieEntity
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