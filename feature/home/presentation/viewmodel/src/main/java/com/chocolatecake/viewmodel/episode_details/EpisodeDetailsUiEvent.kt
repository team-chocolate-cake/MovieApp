package com.chocolatecake.viewmodel.episode_details

sealed interface EpisodeDetailsUiEvent {
    object ClickToBack : EpisodeDetailsUiEvent

    object ClickToRate : EpisodeDetailsUiEvent
    data class ClickCast(val itemId: Int) : EpisodeDetailsUiEvent
    object SubmitRating : EpisodeDetailsUiEvent

}