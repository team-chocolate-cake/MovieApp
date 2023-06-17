package com.chocolatecake.usecase

import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class GetAllGenresTvsUseCase @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke(): List<GenreEntity>{
        repository.refreshGenresTv()
        return repository.getGenresTvs().sortedBy { it.genreName }
    }
}