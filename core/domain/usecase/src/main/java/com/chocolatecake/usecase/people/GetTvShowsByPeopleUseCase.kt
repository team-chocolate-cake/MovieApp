package com.chocolatecake.usecase.people

import com.chocolatecake.entities.PeopleDataEntity
import com.chocolatecake.entities.TvShowsEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvShowsByPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(person_id:Int): List<TvShowsEntity> {
        return movieRepository.getTvShowsByPerson(person_id)
    }
}