package com.chocolatecake.usecase.youtube_trailer

import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMediaTrailerUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(mediaType: MediaType, mediaID: Int): YoutubeVideoDetailsEntity {
        val result = when (mediaType) {
            MediaType.MOVIE -> {
                movieRepository.getTrailerVideoForMovie(mediaID)
            }

            MediaType.TV_SHOW -> {
                movieRepository.getTrailerVideoForTvShow(mediaID)
            }
        }
        return result
    }
}

