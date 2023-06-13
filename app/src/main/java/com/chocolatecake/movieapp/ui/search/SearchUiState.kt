package com.chocolatecake.movieapp.ui.search

data class SearchUiState(
    val query: String = "",
    val mediaType: SearchMedia = SearchMedia.MOVIE,
    val searchMediaResult: List<MediaUIState> = emptyList(),
    val isLoading: Boolean = false,
    val error: List<String>? = null,
    val genresMovies: List<GenresMoviesUiState>? = emptyList(),
    val selectedMovieGenresId: Int? = null,
    val searchHistory: List<String> = emptyList(),
) {
    data class GenresMoviesUiState(
        val genreId: Int = 0,
        val genresName: String = "",
        val isSelected: Boolean = false
    )

    data class MediaUIState(
        val mediaId: Int = 0,
        val mediaType: SearchMedia,
        val mediaImage: String = "",
        val mediaName: String = "",
        val mediaRate: Double = 0.0,
    )
    enum class SearchMedia {
        MOVIE,
        TV,
        PEOPLE
    }
}