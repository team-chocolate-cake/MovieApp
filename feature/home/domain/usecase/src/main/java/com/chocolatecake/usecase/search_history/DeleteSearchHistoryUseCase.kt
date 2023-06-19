package com.chocolatecake.usecase.search_history

import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class DeleteSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String){
        return movieRepository.deleteSearchHistory(keyword = keyword)
    }
}