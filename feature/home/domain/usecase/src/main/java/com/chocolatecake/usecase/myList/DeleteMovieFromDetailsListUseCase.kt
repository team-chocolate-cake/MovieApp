package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class DeleteMovieFromDetailsListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int,mediaId: Int ): StatusEntity {
        return movieRepository.deleteMovieDetailsList(listId =listId , mediaId = mediaId )
    }
}