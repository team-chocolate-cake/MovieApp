package com.chocolatecake.usecase.search

import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class SearchPeopleUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String): List<PeopleEntity>{
        return movieRepository.searchForPeople(keyword)
    }
}