package com.chocolatecake.usecase.myList

import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class CreateListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(nameList:String): Boolean {
        return movieRepository.addList(name = nameList)
    }
}