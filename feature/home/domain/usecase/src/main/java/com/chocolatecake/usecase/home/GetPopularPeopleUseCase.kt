package com.chocolatecake.usecase.home

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetPopularPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val refreshIfNeededUseCase: RefreshIfNeededUseCase
) {
    suspend operator fun invoke(limit: Int = 10): List<PeopleEntity> {
        refreshIfNeededUseCase()
        return movieRepository.getPopularPeople()
            .also { if (it.isEmpty()) movieRepository.refreshPopularPeople() }
            .take(limit)
    }
}