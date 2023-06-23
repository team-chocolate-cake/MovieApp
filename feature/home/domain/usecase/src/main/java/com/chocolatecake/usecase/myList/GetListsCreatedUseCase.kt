package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.myList.ListCreatedEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetListsCreatedUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<ListCreatedEntity> {
        return movieRepository.getListCreated()
    }
}