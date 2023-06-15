package com.chocolatecake.viewmodel.search

data class SearchUiState(
    val mediaType: SearchMedia = SearchMedia.MOVIE,
    val searchMediaResult: SearchItem = SearchItem.MediaItem(emptyList()),
    val searchPeopleResult: SearchItem = SearchItem.PeopleItem(emptyList()),
    val genresMovies: List<GenresMoviesUiState>? = emptyList(),
    val selectedMovieGenresId: Int? = null,
    val searchHistory: List<String> = emptyList(),
    val isSelectedPeople: Boolean = false,
    val isLoading: Boolean = false,
    val error: List<String>? = null,
) {
    data class GenresMoviesUiState(
        val genreId: Int = 0,
        val genresName: String = "",
        val isSelected: Boolean = false
    )

    enum class SearchMedia {
        MOVIE,
        TV,
        PEOPLE
    }
}