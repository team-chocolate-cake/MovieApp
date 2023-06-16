package com.chocolatecake.viewmodel.watch_history.mappers

import com.chocolatecake.entities.MovieInWatchHistoryEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import javax.inject.Inject

class MovieUiStateMapper @Inject constructor() : Mapper<MovieInWatchHistoryEntity, MovieUiState> {
    override fun map(input: MovieInWatchHistoryEntity): MovieUiState {
        return MovieUiState(
            id = input.id,
            title = input.title ?: "",
            description = input.description ?: "",
            rating = input.voteAverage ?: 0.0,
            imageUrl = input.posterPath ?: ""
        )
    }
}