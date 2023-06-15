package com.chocolatecake.viewmodel.people

import com.chocolatecake.viewmodel.home.HomeUiEvent

sealed interface PeopleDetailsUiEvent {
    data class ClickMovieEvent(val itemId: Int) : PeopleDetailsUiEvent
    data class ClickTvShowsEvent(val itemId: Int) : PeopleDetailsUiEvent

    object ClickShowMore : PeopleDetailsUiEvent

}