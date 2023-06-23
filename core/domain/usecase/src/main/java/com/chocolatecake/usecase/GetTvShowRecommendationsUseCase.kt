package com.chocolatecake.usecase

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvShowRecommendationsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int):List<TvShowEntity>{
        return movieRepository.getTvShowRecommendations(tvShowId)
    }
}