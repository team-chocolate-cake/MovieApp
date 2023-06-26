package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.usecase.movie_details.CheckIsLoginedOrNotUseCase
import com.chocolatecake.viewmodel.movieDetails.FavoriteBodyUiState
import com.chocolatecake.viewmodel.movieDetails.UpperUiState
import javax.inject.Inject

class UpperUiStateMapper @Inject constructor(
    private val checkIsLoginedOrNotUseCase: CheckIsLoginedOrNotUseCase
) :
    Mapper<MovieDetailsEntity, UpperUiState> {
    override fun map(input: MovieDetailsEntity): UpperUiState {
        return UpperUiState(
            id = input.id,
            backdropPath = input.backdropPath,
            genres = input.genres,
            title = input.title,
            overview = input.overview,
            voteAverage = input.voteAverage.toFloat().div(2f),
            videos = input.videos.results.map { it.key },
            isLogined = checkIsLoginedOrNotUseCase()
        )
    }

}