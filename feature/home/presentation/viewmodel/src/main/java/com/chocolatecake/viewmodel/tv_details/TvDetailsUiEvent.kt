package com.chocolatecake.viewmodel.tv_details

sealed interface TvDetailsUiEvent {
    object Rate : TvDetailsUiEvent
    data class PlayButton(val youtubeKey: String) : TvDetailsUiEvent
    object ApplyRating : TvDetailsUiEvent
    data class OnPersonClick(val id: Int) : TvDetailsUiEvent
    data class OnSeasonClick(val id: Int) : TvDetailsUiEvent
    data class OnRecommended(val id: Int) : TvDetailsUiEvent
    object Back:TvDetailsUiEvent
}
