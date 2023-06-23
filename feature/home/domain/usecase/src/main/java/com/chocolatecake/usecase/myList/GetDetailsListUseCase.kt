package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetDetailsListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int): List<MovieEntity> {
        return movieRepository.getDetailsList(listId)
    }
}