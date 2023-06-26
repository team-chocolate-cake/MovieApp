package com.chocolatecake.usecase

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMoviesByPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): List<MovieEntity>{
        return movieRepository.getMoviesByPerson(person_id)
    }
}