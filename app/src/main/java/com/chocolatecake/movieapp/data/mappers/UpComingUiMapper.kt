package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.UpcomingMovieEntity
import com.chocolatecake.movieapp.ui.home.ui_state.UpComingMoviesUiState
import javax.inject.Inject

class UpComingUiMapper @Inject constructor() : Mapper<UpcomingMovieEntity, UpComingMoviesUiState> {
    override fun map(input: UpcomingMovieEntity): UpComingMoviesUiState {
        return UpComingMoviesUiState(
            input.id,
            input.imageUrl
        )
    }
}