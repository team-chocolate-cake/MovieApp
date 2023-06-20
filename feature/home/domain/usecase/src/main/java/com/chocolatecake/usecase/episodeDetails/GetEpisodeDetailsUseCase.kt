package com.chocolatecake.usecase.episodeDetails

import com.chocolatecake.entities.EpisodeDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetEpisodeDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        seriesId: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): EpisodeDetailsEntity {
        return movieRepository.getEpisodeDetails(seriesId,seasonNumber,episodeNumber)
    }
}