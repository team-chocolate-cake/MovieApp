package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetFavoritesByMediaTypeUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(mediaType: String): List<MovieEntity> {
        return  movieRepository.getFavoriteByMediaType(mediaType)
    }
}