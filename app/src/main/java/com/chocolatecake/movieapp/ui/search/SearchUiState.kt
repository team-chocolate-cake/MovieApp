package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.domain.model.TvEntity

data class SearchUiState(
    val query: String = "",
    val mediaType: List<SearchTypeUiState> = emptyList(),
    val searchMovieResult: List<Movie> = emptyList(),
    val searchTvShowResult: List<TvEntity> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<String>? = null,
    val genresMovies: List<GenresMoviesUiState>? = emptyList(),
    val selectedMovieGenresId: Int? = null,
    val searchHistory: List<String> = emptyList(),
) {
    data class SearchTypeUiState(
        val typeName: String = "",
        val isTypeSelected: Boolean = false
    )

    data class TvShowUiState(
        val id: Int = 0,
        val imageUrl: String = ""
    )

    data class GenresMoviesUiState(
        val genreId: Int = 0,
        val genresName: String = "",
        val isSelected: Boolean = false
    )
}