package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class DeleteListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int): StatusEntity {
        return movieRepository.deleteList(listId =listId)
    }
}
