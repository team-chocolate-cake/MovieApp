package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMyListDetailsByListIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int = 0): List<MovieEntity> {
        return  movieRepository.getDetailsList(listId)
    }
}