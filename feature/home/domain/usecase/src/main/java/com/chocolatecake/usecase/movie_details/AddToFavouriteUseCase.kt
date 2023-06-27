package com.chocolatecake.usecase.movie_details

import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository
) {
    suspend operator fun invoke(movieId:Int,mediaType:String , isFavorite:Boolean = true): StatusEntity {
        return movieRepository.addFavouriteList(movieId,mediaType,isFavorite)
    }
}