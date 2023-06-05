package com.chocolatecake.movieapp.domain.mappers.genre

import com.chocolatecake.movieapp.data.remote.response.GenreMovieDto
import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.Genre
import javax.inject.Inject

class GenreMapper @Inject constructor() : Mapper<GenreMovieDto, Genre> {
    override fun map(input: GenreMovieDto): Genre {
        return Genre(
            genreID = input.id ?: 0,
            genreName = input.name ?: ""
        )
    }
}