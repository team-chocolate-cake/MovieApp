package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.ui.search.SearchUiState
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieUIMapper: MovieUIMapper
) {
    suspend operator fun invoke(keyword: String,genreId: Int?): List<SearchUiState.MediaUIState> {
        val movies = movieRepository.searchForMovies(keyword)
        return movies.filter { movie ->
            movie.genreIds.takeIf { genreId != null }?.contains(genreId) ?: true && movie.voteAverage != 0.0
        }.sortedByDescending { it.voteAverage }.map { movie ->
            val formattedVoteAverage = "%.1f".format(movie.voteAverage)
            movieUIMapper.map(movie.copy(voteAverage = formattedVoteAverage.toDouble()))
        }
    }
}