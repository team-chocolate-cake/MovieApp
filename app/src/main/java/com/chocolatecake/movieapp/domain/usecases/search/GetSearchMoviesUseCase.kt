package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.domain.model.Movie
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieUIMapper: MovieUIMapper
) {
    suspend operator fun invoke(keyword: String): List<Movie> {
        return movieRepository.getSearchMovies(keyword = keyword)
            .map { movieUIMapper.map(it) }
            .sortedBy { it.title }
    }
}