package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.myList.ListMovieEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class AddMovieToListUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(movie: ListMovieEntity): Boolean {
        return movieRepository.addMovieToList(movie)
    }
}