package com.chocolatecake.viewmodel.tv_details

sealed interface TvDetailsUiEvent {
    object Rate : TvDetailsUiEvent
    data class PlayButton(val youtubeKey: String) : TvDetailsUiEvent
    object ApplyRating : TvDetailsUiEvent
}
