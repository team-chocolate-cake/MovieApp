package com.chocolatecake.usecase

import android.util.Log
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsReviewsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<ReviewEntity> {
        val items = movieRepository.getTvShowReviews()
        Log.i("reviews", "the reviews are $items")
        return items
    }
}