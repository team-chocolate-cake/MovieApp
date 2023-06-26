package com.chocolatecake.usecase

import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetPeopleDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): PeopleDataEntity{
        return movieRepository.getPersonDetails(person_id)
    }
}