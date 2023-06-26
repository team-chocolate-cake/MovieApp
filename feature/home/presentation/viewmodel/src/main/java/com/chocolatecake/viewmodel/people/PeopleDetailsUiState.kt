package com.chocolatecake.viewmodel.people

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState


data class PeopleDetailsUiState(
    val peopleData :PeopleDataUiState =PeopleDataUiState(0,"","","","","","","") ,
    val Movies: List<PeopleMediaUiState> = emptyList(),
    val TvShows: List<PeopleMediaUiState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = false
) {

    data class PeopleMediaUiState(
        val id: Int,
        val name:String,
        val imageUrl: String,
        val rate: Double
    )

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