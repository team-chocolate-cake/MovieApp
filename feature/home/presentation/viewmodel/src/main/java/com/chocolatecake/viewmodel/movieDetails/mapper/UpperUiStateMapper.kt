package com.chocolatecake.viewmodel.movieDetails.mapper

import com.chocolatecake.entities.movieDetails.MovieDetailsEntity
import com.chocolatecake.entities.movieDetails.MovieVideoEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.movieDetails.UpperUiState
import javax.inject.Inject

class UpperUiStateMapper @Inject constructor() :
    Mapper<MovieDetailsEntity, UpperUiState> {
    override fun map(input: MovieDetailsEntity): UpperUiState {
        return UpperUiState(
            id = input.id,
            backdropPath = input.backdropPath,
            genres = input.genres,
            title = input.title,
            overview = input.overview,
            voteAverage = input.voteAverage.toFloat().div(2f),
            videoKey = getTheFirstVideoKeyInList(input.videos.results),
        )
    }

    private fun getTheFirstVideoKeyInList(results: List<MovieVideoEntity>): String {
        return if (results.isNotEmpty()) results.map { it.key }.first() else ""
    }

}