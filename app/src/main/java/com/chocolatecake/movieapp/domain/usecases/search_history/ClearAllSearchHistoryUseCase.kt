package com.chocolatecake.movieapp.domain.usecases.search_history

import com.chocolatecake.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class ClearAllSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(){
        return movieRepository.clearAllSearchHistory()
    }
}