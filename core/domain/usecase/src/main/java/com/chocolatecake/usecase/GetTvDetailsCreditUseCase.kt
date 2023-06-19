package com.chocolatecake.usecase

import com.chocolatecake.entities.CastEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetTvDetailsCreditUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(tvShowId:Int): List<PeopleEntity> {
        return movieRepository.getTvDetailsCredit(tvShowId)
    }
}