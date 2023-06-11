package com.chocolatecake.movieapp.domain.usecases.genres

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.mappers.genre.GenreMapper
import com.chocolatecake.movieapp.domain.model.Genre
import javax.inject.Inject

class GetAllGenresMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val genreMapper: GenreMapper
) {
    suspend operator fun invoke(): List<Genre> {
        return movieRepository.getGenresMovies()?.map {
            genreMapper.map(it)
        }?.sortedBy { it.genreName } ?: emptyList()
    }
}