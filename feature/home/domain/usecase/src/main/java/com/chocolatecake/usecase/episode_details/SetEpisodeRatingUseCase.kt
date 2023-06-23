package com.chocolatecake.usecase.episode_details

import com.chocolatecake.entities.RatingEpisodeDetailsStatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class SetEpisodeRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        value: Float
    ): RatingEpisodeDetailsStatusEntity {
        return movieRepository.setRatingForEpisode(seriesId, seasonNumber, episodeNumber, value)
    }
}