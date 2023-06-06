package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.remote.response.MovieDto
import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.search.MovieUIMapper
import com.chocolatecake.movieapp.domain.model.Movie
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val movieUIMapper: MovieUIMapper
) {
    suspend operator fun invoke(keyword: String, genreId: Int?): List<Movie> {
        val movies = movieRepository.getSearchMovies(keyword)
        return movies.filter { movie -> filterMovies(movie, genreId) }
            .sortedByDescending { it.voteAverage }
            .map(::mapMovie)
    }

    private fun filterMovies(movie: MovieDto, genreId: Int?): Boolean {
        return movie.genreIds.takeIf { genreId != null }?.contains(genreId) ?: true &&
                movie.voteAverage != 0.0 &&
                movie.posterPath!!.isNotEmpty()
    }

    private fun mapMovie(movie: MovieDto): Movie {
        val formattedVoteAverage = "%.1f".format(movie.voteAverage)
        return movieUIMapper.map(movie.copy(voteAverage = formattedVoteAverage.toDouble()))
    }

}