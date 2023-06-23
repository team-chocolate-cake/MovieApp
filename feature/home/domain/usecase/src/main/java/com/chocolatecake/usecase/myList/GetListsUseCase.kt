package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.myList.ListEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetListsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<ListEntity> {
        return movieRepository.getLists()
    }
}