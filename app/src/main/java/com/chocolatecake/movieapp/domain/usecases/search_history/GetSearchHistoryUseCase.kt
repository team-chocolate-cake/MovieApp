package com.chocolatecake.movieapp.domain.usecases.search_history

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.model.SearchHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistoryUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(keyword: String): Flow<List<SearchHistory>> {
        return movieRepository.getSearchHistory(keyword = keyword)
    }
}