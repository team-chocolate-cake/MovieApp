package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int): MovieDetailsEntity {
        return movieRepository.getMoviesDetails(movieId)
    }
}