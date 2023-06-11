package com.chocolatecake.movieapp.domain.usecases.search

import android.util.Log
import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.domain.model.Movie
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String,genreId: Int?): List<MovieDto> {
        val movies = movieRepository.getSearchMovies(keyword)
        return movies.filter { movie ->
            movie.genreIds.takeIf { genreId != null }?.contains(genreId) ?: true && movie.voteAverage != 0.0
        }.sortedByDescending { it.voteAverage }

    }
}