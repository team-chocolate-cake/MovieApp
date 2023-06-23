package com.chocolatecake.usecase.episode_details

import android.util.Log
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetCastForEpisodeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(
        id: Int,
        seasonNumber: Int,
        episodeNumber: Int
    ): List<PeopleEntity> {
        return movieRepository.getCastForEpisode(id, seasonNumber, episodeNumber)
    }
}