package com.chocolatecake.movieapp.data.mappers

import com.chocolatecake.movieapp.data.local.database.entity.movie.NowPlayingMovieEntity
import com.chocolatecake.movieapp.ui.home.ui_state.NowPlayingUiState
import javax.inject.Inject

class NowPlayingUiMapper @Inject constructor() : Mapper<NowPlayingMovieEntity, NowPlayingUiState> {
    override fun map(input: NowPlayingMovieEntity): NowPlayingUiState {
        return NowPlayingUiState(
            input.id,
            input.imageUrl
        )
    }
}