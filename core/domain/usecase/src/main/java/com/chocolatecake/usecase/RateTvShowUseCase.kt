package com.chocolatecake.usecase

import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class RateTvShowUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(rate: Double, tvShowId: Int): TvRatingEntity {
        return movieRepository.rateTvShow(rate, tvShowId)
    }
}