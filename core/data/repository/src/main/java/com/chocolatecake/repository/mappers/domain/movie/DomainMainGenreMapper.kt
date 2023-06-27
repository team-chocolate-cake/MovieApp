package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.local.database.dto.GenresMoviesLocalDto
import com.chocolatecake.remote.response.movieDetails.Genre
import com.chocolatecake.repository.mappers.Mapper
import javax.inject.Inject

class DomainMainGenreMapper @Inject constructor() : Mapper<Genre, GenreEntity> {
    override fun map(input: Genre): GenreEntity {
        return GenreEntity(
            genreID =  input.id?:0,
            genreName = input.name?:"",
        )
    }
}