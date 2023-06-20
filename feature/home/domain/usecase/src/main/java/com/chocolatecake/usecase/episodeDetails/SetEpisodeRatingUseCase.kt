package com.chocolatecake.usecase.episodeDetails

import com.chocolatecake.entities.RatingEpisodeDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class SetEpisodeRatingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int,
        value: Int
    ): RatingEpisodeDetailsEntity {
        return movieRepository.setRatingForEpisode(seriesId,seasonNumber,episodeNumber,value)
    }
}