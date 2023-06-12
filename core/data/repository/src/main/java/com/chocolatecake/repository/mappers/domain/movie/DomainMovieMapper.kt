package com.chocolatecake.repository.mappers.domain.movie

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.remote.response.dto.MovieRemoteDto
import com.chocolatecake.repository.BuildConfig
import com.chocolatecake.repository.mappers.Mapper
import com.chocolatecake.usecase.GetAllGenresMoviesUseCase
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class DomainMovieMapper @Inject constructor(
    private val getAllGenresMoviesUseCase: GetAllGenresMoviesUseCase,
) : Mapper<MovieRemoteDto, MovieEntity> {
    private val genres = runBlocking { getAllGenresMoviesUseCase() }

    override fun map(input: MovieRemoteDto): MovieEntity {
        return MovieEntity(
            id = input.id ?: 0,
            rate = input.voteAverage ?: 0.0,
            title = input.title ?: "",
            genreEntities = filterGenres(input.genreIds?.filterNotNull() ?: emptyList()),
            imageUrl = BuildConfig.IMAGE_BASE_PATH + input.posterPath,
        )
    }

    private fun filterGenres(genresIds: List<Int>): List<GenreEntity>
    = genres.filter{ it.genreID in genresIds }

}