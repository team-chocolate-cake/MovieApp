package com.chocolatecake.movieapp.domain.usecases.search

import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.model.TvEntity
import javax.inject.Inject


class SearchTvUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(keyword: String): List<TvEntity> {
         return movieRepository.searchForTv(keyword).sortedByDescending { it.voteAverage }
    }
}