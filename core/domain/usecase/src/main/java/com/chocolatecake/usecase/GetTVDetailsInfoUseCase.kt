package com.chocolatecake.usecase

import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTVDetailsInfoUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): TvDetailsInfoEntity {
        return movieRepository.getTvDetails()
    }
}