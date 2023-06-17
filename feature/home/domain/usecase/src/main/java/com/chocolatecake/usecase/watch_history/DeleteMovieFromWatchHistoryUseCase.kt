package com.chocolatecake.usecase.watch_history


import com.chocolatecake.entities.MovieInWatchHistoryEntity
import javax.inject.Inject

class DeleteMovieFromWatchHistoryUseCase @Inject constructor(
    private val repository: WatchHistoryRepository,
) {
    suspend operator fun invoke(movie: MovieInWatchHistoryEntity) {
        repository.deleteMovieFromWatchHistory(movie)
    }
}