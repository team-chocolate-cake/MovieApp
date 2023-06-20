package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.movieDetails.RatingResponseEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int , rate:Float): RatingResponseEntity {
        return movieRepository.setMovieRate(movieId , rate)
    }
}