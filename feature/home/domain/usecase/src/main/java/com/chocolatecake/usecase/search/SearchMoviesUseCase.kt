package com.chocolatecake.usecase.search

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(
        keyword: String,
        genreId: Int? = null
    ): List<MovieEntity> {

        return movieRepository.getSearchMovies(keyword).filter { movie ->
            movie.genreEntities.takeIf { genreId != null }
                ?.map { it.genreID }
                ?.contains(genreId) ?: true && movie.rate != 0.0
        }.sortedByDescending { it.rate }
    }
}