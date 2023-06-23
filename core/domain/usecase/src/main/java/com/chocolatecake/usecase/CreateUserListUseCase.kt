package com.chocolatecake.usecase

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class CreateUserListUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(listName: String): StatusEntity {
        return movieRepository.createUserList(listName)
    }
}