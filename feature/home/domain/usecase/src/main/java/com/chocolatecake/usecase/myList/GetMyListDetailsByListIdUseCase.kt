package com.chocolatecake.usecase.myList

import androidx.paging.Pager
import androidx.paging.PagingData
import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMyListDetailsByListIdUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(listId: Int = 0): List<MovieEntity> {
        return  movieRepository.getFavoriteMovies()
    }
}