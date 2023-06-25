package com.chocolatecake.usecase.myList

import com.chocolatecake.entities.myList.WatchlistRequestEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class MakeAsWatchlistUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(watchlistBody : WatchlistRequestEntity): Boolean {
        return movieRepository.addWatchlist(watchlistBody)
    }
}