package com.chocolatecake.usecase.myList


import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.repository.MovieRepository
import javax.inject.Inject

class MakeAsFavoriteUseCase @Inject constructor(
    private val movieRepository: MovieRepository,
) {
    suspend operator fun invoke(favoriteBody : FavoriteBodyRequestEntity): Boolean {
        return movieRepository.addFavoriteMovie(favoriteBody)
    }
}