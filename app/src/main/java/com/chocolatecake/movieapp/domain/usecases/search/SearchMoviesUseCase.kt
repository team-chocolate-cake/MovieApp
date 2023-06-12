package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.domain.entities.GenreEntity
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import com.chocolatecake.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(keyword: String, genreEntity: GenreEntity? = null): List<MovieEntity> {

        return movieRepository.getSearchMovies(keyword).filter { movie ->
            movie.genreEntities.takeIf { genreEntity != null }
                ?.contains(genreEntity) ?: true && movie.rate != 0.0
        }.sortedByDescending { it.rate }
    }
}