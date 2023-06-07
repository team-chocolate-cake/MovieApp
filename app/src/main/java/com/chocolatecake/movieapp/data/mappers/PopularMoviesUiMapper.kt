package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.PopularMovieEntity
import com.chocolatecake.movieapp.ui.home.ui_state.PopularMoviesUiState
import javax.inject.Inject

class PopularMoviesUiMapper @Inject constructor() :
    Mapper<PopularMovieEntity, PopularMoviesUiState> {
    override fun map(input: PopularMovieEntity): PopularMoviesUiState {
        return PopularMoviesUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}