package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.domain.mappers.Mapper
import com.chocolatecake.movieapp.domain.model.Genre
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiState
import javax.inject.Inject

class GenreUiStateMapper @Inject constructor() : Mapper<Genre, SearchUiState.GenresMoviesUiState> {
    override fun map(input: Genre): SearchUiState.GenresMoviesUiState {
        return SearchUiState.GenresMoviesUiState(
            genreId = input.genreID,
            genresName = input.genreName
        )
    }
}