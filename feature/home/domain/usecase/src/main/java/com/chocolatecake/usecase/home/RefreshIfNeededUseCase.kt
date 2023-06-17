package com.chocolatecake.usecase.home

import com.chocolatecake.repository.MovieRepository
import java.util.Date
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RefreshIfNeededUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
    private val currentDate: Date
) {
    suspend operator fun invoke(): Boolean {
        val lastRefreshTime = movieRepository.getLastRefreshTime()

        if (lastRefreshTime == null ||
            currentDate.time - lastRefreshTime >= TimeUnit.DAYS.toMillis(1)
        ) {
            movieRepository.setLastRefreshTime(currentDate.time)
            movieRepository.refreshAll()
            return true
        }

        return false
    }
}