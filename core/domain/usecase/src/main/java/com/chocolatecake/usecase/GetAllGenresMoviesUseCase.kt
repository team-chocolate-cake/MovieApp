package com.chocolatecake.usecase


import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetAllGenresMoviesUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<GenreEntity> {
        movieRepository.refreshGenres()
        return movieRepository.getGenresMovies().sortedBy { it.genreName }
    }
}