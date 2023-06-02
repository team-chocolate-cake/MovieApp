package com.chocolatecake.movieapp.domain.usecases.home

import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.data.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take
import java.util.Random
import javax.inject.Inject

class GetPopularUseCase @Inject constructor(
private val   movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<List<PopularMovieEntity>> {

        return movieRepository.getPopularMovies().map {
            val random=Random()
            it.shuffled(random).take(10)
        }
    }
}