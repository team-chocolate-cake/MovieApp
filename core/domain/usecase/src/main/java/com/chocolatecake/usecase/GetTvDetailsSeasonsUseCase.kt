package com.chocolatecake.usecase

import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsSeasonsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<SeasonEntity> {
        return movieRepository.getTvDetailsSeasons()
    }
}