package com.chocolatecake.usecase

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class RateTvShowUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(rate: Double, tvShowId: Int): StatusEntity {
        return movieRepository.rateTvShow(rate, tvShowId)
    }
}