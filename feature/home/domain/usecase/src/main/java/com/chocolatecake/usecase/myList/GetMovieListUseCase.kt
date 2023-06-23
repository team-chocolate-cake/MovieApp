package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.myList.ListMovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetMovieListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(): List<ListMovieEntity>{
        return movieRepository.getMovieList()
    }
}