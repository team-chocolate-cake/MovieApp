package com.chocolatecake.viewmodel.tv_shows

import com.chocolatecake.entities.GenreEntity
import javax.inject.Inject

class GenreTVUiMapper @Inject constructor() {
    fun map(input: GenreEntity, isSelected: Boolean): GenresTVShowsUiState {
        return GenresTVShowsUiState(
            genreId = input.genreID,
            genresName = input.genreName,
            isSelected = isSelected
        )
    }
}