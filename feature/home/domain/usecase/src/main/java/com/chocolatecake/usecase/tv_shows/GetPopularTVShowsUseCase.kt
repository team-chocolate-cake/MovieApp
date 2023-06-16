package com.chocolatecake.usecase.tv_shows

import android.util.Log
import androidx.paging.PagingData
import com.chocolatecake.entities.TVShowsEntity
import com.chocolatecake.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularTVShowsUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(): Flow<PagingData<TVShowsEntity>> {
        Log.d("list---UseCase---Popular", movieRepository.getPopularTVShows().toString())
        return movieRepository.getPopularTVShows().flow
    }
}