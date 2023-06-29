package com.chocolatecake.viewmodel.search

import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState

data class SearchUiState(
    val mediaType: SearchMedia = SearchMedia.MOVIE,
    val searchMediaResult: List<MovieHorizontalUIState> = emptyList(),
    val searchPeopleResult: List<PeopleUIState> = emptyList(),
    val genres: List<GenresUiState> = emptyList(),
    val selectedGenresId: Int? = null,
    val searchHistory: List<String> = emptyList(),
    val isSelectedPeople: Boolean = false,
    val isLoading: Boolean = false,
    val error: List<String>? = null,
) {
    data class GenresUiState(
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

    val isEmptyResult: Boolean get() =
        when(mediaType){
            SearchMedia.MOVIE, SearchMedia.TV -> searchMediaResult.isEmpty()
            SearchMedia.PEOPLE -> searchPeopleResult.isEmpty()
        }
}