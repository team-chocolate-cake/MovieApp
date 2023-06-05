package com.chocolatecake.movieapp.domain.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.TrendingMoviesEntity
import com.chocolatecake.movieapp.ui.home.ui_state.TrendingMoviesUiState
import javax.inject.Inject

class TrendingUiMapper @Inject constructor()  : Mapper<TrendingMoviesEntity, TrendingMoviesUiState> {
    override fun map(input: TrendingMoviesEntity): TrendingMoviesUiState {
        return TrendingMoviesUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}