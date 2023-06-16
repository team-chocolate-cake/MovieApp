package com.chocolatecake.usecase

import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class RateTvShowUseCase @Inject constructor(
    private val moviewRepository: MovieRepository
) {
    suspend operator fun invoke(rate: Double, tvShow: Int): TvRatingEntity {
        return moviewRepository.rateTvShow(rate, tvShow)
    }
}