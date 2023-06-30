package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class SetRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int , rate:Float): StatusEntity {
        return movieRepository.setMovieRate(movieId , rate)
    }
}