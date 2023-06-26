package com.chocolatecake.usecase

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvShowYoutubeDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId: Int): YoutubeVideoDetailsEntity {
        return movieRepository.getTrailerVideoForTvShow(tvShowId)
    }
}