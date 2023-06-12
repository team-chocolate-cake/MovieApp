package com.chocolatecake.viewmodel.search

import com.chocolatecake.entities.MovieEntity
import com.chocolatecake.mapper.Mapper
import javax.inject.Inject

class MovieUiMapper @Inject constructor()  : Mapper<MovieEntity, SearchUiState.MoviesUiState> {
    override fun map(input: MovieEntity): SearchUiState.MoviesUiState {
        return SearchUiState.MoviesUiState(
            input.id,
            input.imageUrl,
            input.rate
        )
    }
}