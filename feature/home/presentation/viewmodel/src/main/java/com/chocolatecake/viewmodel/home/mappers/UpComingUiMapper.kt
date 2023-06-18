package com.chocolatecake.viewmodel.home.mappers

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.viewmodel.home.UpComingMoviesUiState
import javax.inject.Inject

class UpComingUiMapper @Inject constructor() : Mapper<MovieEntity, UpComingMoviesUiState> {
    override fun map(input: MovieEntity): UpComingMoviesUiState {
        return UpComingMoviesUiState(
            input.id,
            input.imageUrl
        )
    }
}