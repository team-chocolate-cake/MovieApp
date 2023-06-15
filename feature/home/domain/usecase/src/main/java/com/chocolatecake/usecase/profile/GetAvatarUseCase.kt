package com.chocolatecake.usecase.profile

import com.chocolatecake.entities.ProfileEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetAvatarUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): List<ProfileEntity> {
        return movieRepository.getAccountDetails()
    }
}