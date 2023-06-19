package com.chocolatecake.viewmodel.home.mappers

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.home.NowPlayingUiState
import javax.inject.Inject

class NowPlayingUiMapper @Inject constructor() : Mapper<MovieEntity, NowPlayingUiState> {
    override fun map(input: MovieEntity): NowPlayingUiState {
        return NowPlayingUiState(
            input.id,
            input.imageUrl
        )
    }
}