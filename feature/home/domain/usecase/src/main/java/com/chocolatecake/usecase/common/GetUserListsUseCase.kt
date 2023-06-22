package com.chocolatecake.usecase.common

import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetUserListsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<UserListEntity> {
        return movieRepository.getUserLists()
    }
}