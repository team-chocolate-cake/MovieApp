package com.chocolatecake.viewmodel.home.mappers

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.home.TopRatedUiState
import javax.inject.Inject

class TopRatedUiMapper @Inject constructor()  : Mapper<MovieEntity, TopRatedUiState> {
    override fun map(input: MovieEntity): TopRatedUiState {
        return TopRatedUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}