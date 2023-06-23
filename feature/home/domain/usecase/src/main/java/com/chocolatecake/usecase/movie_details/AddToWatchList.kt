package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class AddToWatchList @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int,mediaType:String): StatusEntity {
        return movieRepository.addWatchlist(movieId,mediaType,true)
    }
}