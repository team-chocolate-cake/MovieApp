package com.chocolatecake.viewmodel.search

import com.chocolatecake.entities.GenreEntity
import javax.inject.Inject

class GenreUiStateMapper @Inject constructor() {
    fun map(input: GenreEntity, isSelected: Boolean): SearchUiState.GenresMoviesUiState {
        return SearchUiState.GenresMoviesUiState(
            genreId = input.genreID,
            genresName = input.genreName,
            isSelected = isSelected
        )
    }
}