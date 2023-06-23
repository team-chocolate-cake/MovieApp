package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.movieDetails.ReviewResponseEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMovieReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int , page:Int): ReviewResponseEntity {
        return movieRepository.getMovieReviews(movieId , page)
    }
}