package com.chocolatecake.viewmodel.search

import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState

data class SearchUiState(
    val mediaType: SearchMedia = SearchMedia.MOVIE,
    val searchMediaResult: List<MovieHorizontalUIState> = emptyList(),
    val searchPeopleResult: List<PeopleUIState> = emptyList(),
    val genresMovie: List<GenresMoviesUiState> = emptyList(),
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

    val isFailure: Boolean get() =
        error?.isNotEmpty() == true
    val hideResult: Boolean get() =
        searchMediaResult.isNullOrEmpty() && searchPeopleResult.isNullOrEmpty()
}