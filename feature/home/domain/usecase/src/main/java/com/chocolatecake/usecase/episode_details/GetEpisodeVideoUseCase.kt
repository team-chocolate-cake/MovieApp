package com.chocolatecake.usecase.episode_details

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetEpisodeVideoUseCase  @Inject constructor(
    private val movieRepository: MovieRepository
)  {
    suspend operator fun invoke(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int,
    ): YoutubeVideoDetailsEntity {
        return movieRepository.getVideoEpisodeDetails(id, seasonNumber, episodeNumber)
    }
}