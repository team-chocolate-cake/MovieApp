package com.chocolatecake.usecase.watch_history


import com.chocolatecake.entities.MovieInWatchHistoryEntity
import javax.inject.Inject

class SearchWatchHistoryUseCase @Inject constructor(
    private val repository: WatchHistoryRepository,
) {
    suspend operator fun invoke(keyword: String): List<MovieInWatchHistoryEntity> {
        return repository.searchWatchHistoryWithKeyWord(keyword)
    }
}