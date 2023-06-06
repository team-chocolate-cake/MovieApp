package com.chocolatecake.movieapp.domain.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.RecommendedMovieEntity
import com.chocolatecake.movieapp.ui.home.ui_state.RecommendedUiState
import javax.inject.Inject

class RecommendedUiMapper @Inject constructor()  : Mapper<RecommendedMovieEntity, RecommendedUiState> {
    override fun map(input: RecommendedMovieEntity): RecommendedUiState {
        return RecommendedUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}