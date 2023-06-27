package com.chocolatecake.viewmodel.people
data class PersonDetailsUiState(
    val peopleData :PersonInfoUiState =PersonInfoUiState(),
    val movies: List<PeopleMediaUiState> = emptyList(),
    val tvShows: List<PeopleMediaUiState> = emptyList(),
    val onErrors: List<String> = emptyList(),
    val isLoading: Boolean = true
) {
    data class PeopleMediaUiState(
        val id: Int,
        val name:String,
        val imageUrl: String,
        val rate: Double
    )
    data class PersonInfoUiState(
        val id: Int =0,
        val name: String="",
        val imageUrl: String="",
        val placeOfBirth: String="",
        val gender: String="",
        val acting: String="",
        val num_movies: String="",
        val biography: String ="" )
}