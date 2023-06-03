package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String): Flow<List<Movie>> {
        return movieRepository.getSearchMovies(keyword = keyword )
    }
}