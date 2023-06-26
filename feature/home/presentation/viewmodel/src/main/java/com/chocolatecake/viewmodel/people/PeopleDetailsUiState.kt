package com.chocolatecake.viewmodel.people

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState


data class PeopleDetailsUiState(
    val peopleData :PeopleDataUiState =PeopleDataUiState(0,"","","","","","","") ,
    val Movies: List<MediaVerticalUIState> = emptyList(),
    val TvShows: List<MediaVerticalUIState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false
) {

//    data class MoviesUiState(
//        val id: Int,
//        val imageUrl: String,
//        val rate: Double
//    )
//
//    data class TvShowsUiState(
//        val id: Int,
//        val imageUrl: String,
//        val rate: Double
//    )

    data class PeopleDataUiState(
        val id: Int,
        val name: String,
        val imageUrl: String,
        val placeOfBirth: String,
        val gender: String,
        val acting: String,
        val num_movies: String,
        val biography: String    )
}