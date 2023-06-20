package com.chocolatecake.usecase.showmore

import androidx.paging.PagingData
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetShowMoreTopRatedByTypeUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(genreId: Int? = null): Flow<PagingData<MovieEntity>> {
        return movieRepository.getTopRateMoviesPaging().flow
    }
}