package com.chocolatecake.usecase

import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvShowsByPersonUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): List<TvShowEntity> {
        return movieRepository.getTvShowsByPerson(person_id)
    }
}