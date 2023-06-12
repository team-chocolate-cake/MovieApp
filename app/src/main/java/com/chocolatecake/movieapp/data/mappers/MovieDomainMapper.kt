package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.domain.entities.GenreEntity
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import com.chocolatecake.movieapp.domain.usecases.genres.GetAllGenresMoviesUseCase
import javax.inject.Inject

class MovieDomainMapper @Inject constructor(
    private val allGenres: List<GenreEntity>
) : Mapper<MovieDto, MovieEntity>{

    override fun map(input: MovieDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            rate = input.voteAverage ?: 0.0,
            title = input.title ?: "",
            genreEntities = genreMapper.map(input.genreIds),
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }
}