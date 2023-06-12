package com.chocolatecake.movieapp.domain.usecases.home

import com.chocolatecake.movieapp.domain.entities.MovieEntity
import com.chocolatecake.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class GetTopRatedUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(limit: Int = 10): List<MovieEntity> {
        return movieRepository.getTopRatedMovies().take(limit)
    }
}