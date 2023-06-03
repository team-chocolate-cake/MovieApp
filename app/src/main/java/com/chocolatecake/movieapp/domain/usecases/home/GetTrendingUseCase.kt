package com.chocolatecake.movieapp.domain.usecases.home

import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import java.util.Random
import javax.inject.Inject

class GetTrendingUseCase @Inject constructor(
    private val movieRepository: MovieRepository
)  {
    suspend operator fun invoke():Flow<List<TrendingMoviesEntity>> {
        return movieRepository.getTrendingMovies().map {
            it.shuffled(Random())
        }.take(10)
    }
}