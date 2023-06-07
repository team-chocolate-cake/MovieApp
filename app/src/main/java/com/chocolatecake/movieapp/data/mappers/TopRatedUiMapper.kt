package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.TopRatedMovieEntity
import com.chocolatecake.movieapp.ui.home.ui_state.TopRatedUiState
import javax.inject.Inject

class TopRatedUiMapper @Inject constructor()  : Mapper<TopRatedMovieEntity, TopRatedUiState> {
    override fun map(input: TopRatedMovieEntity): TopRatedUiState {
        return TopRatedUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}