package com.chocolatecake.viewmodel.search

data class SearchUiState(
    val mediaType: List<SearchTypeUiState> = emptyList(),
    val searchMovieResultEntity: List<MoviesUiState> = emptyList(),
    val searchTvShowResult: List<TvShowUiState> = emptyList(),
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

    data class MoviesUiState(
        val id: Int,
        val imageUrl: String,
        val rate: Double
    )
}