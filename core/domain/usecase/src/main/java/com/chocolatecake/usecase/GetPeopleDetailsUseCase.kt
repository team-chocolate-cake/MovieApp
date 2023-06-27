package com.chocolatecake.usecase

import com.chocolatecake.entities.PeopleDetailsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetPeopleDetailsUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): PeopleDetailsEntity{
        return movieRepository.getPersonDetails(person_id)
    }
}