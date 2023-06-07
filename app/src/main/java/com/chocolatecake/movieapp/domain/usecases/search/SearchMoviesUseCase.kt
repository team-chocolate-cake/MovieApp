package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.domain.model.Genre
import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(keyword: String, genre: Genre? = null): List<Movie> {

        return movieRepository.getSearchMovies(keyword).filter { movie ->
            movie.genres.takeIf { genre != null }
                ?.contains(genre) ?: true && movie.voteAverage != 0.0
        }.sortedByDescending { it.voteAverage }
    }
}