package com.chocolatecake.movieapp.domain.usecases.home

import com.chocolatecake.movieapp.data.local.database.entity.actor.PopularPeopleEntity
import com.chocolatecake.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.util.Random
import javax.inject.Inject

class GetPopularPeopleUsecase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<List<PopularPeopleEntity>>{

        return movieRepository.getPopularPeople().map {
            val random= Random()
            it.shuffled(random).take(10)
        }
    }
}