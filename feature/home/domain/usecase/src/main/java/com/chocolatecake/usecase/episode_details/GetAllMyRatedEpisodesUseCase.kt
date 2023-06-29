package com.chocolatecake.usecase.episode_details

import androidx.paging.PagingData
import com.chocolatecake.entities.my_rated.MyRatedEpisodesEntity
import com.chocolatecake.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMyRatedEpisodesUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<MyRatedEpisodesEntity>> {
        return movieRepository.getAllMyRatedEpisodes().flow
    }
}