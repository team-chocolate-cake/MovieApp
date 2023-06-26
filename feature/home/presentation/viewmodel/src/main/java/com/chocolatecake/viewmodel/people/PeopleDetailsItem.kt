package com.chocolatecake.viewmodel.people

import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState

sealed class PeopleDetailsItem(val type: PeopleDetailsItemType) {
   data class PeopleData(val peopleDataUiState: PeopleDetailsUiState.PeopleDataUiState):PeopleDetailsItem(PeopleDetailsItemType.PEOPLE_DATA)
    data class Movies(val list: List<MediaVerticalUIState>) : PeopleDetailsItem(PeopleDetailsItemType.MOVIES)
    data class TvShows(val list: List<MediaVerticalUIState>) : PeopleDetailsItem(PeopleDetailsItemType.TV_SHOWS)
}

enum class PeopleDetailsItemType {PEOPLE_DATA, MOVIES, TV_SHOWS }