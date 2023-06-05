package com.chocolatecake.movieapp.domain.usecases.search_history

import com.chocolatecake.movieapp.data.repository.MovieRepository
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(keyword: String): List<String> {
        return movieRepository.getSearchHistory(keyword = keyword)
            .map { it.keyword }.sortedBy { keyword }
    }
}