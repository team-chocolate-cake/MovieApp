package com.chocolatecake.usecase.episodeDetails

import com.chocolatecake.entities.CastEpisodeDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetCastForEpisodeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): List<CastEpisodeDetailsEntity> {
        return movieRepository.getCastForEpisode(id, seasonNumber, episodeNumber)
    }
}