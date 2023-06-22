package com.chocolatecake.usecase.common

import com.chocolatecake.entities.movieDetails.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class AddToUserListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(listId: Int, mediaId: Int): StatusEntity {
        return movieRepository.postUserLists(listId, mediaId)
    }
}