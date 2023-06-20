package com.chocolatecake.viewmodel.episode_details

sealed interface EpisodeDetailsUiEvent {
    object ClickToRate : EpisodeDetailsUiEvent
    object PlayEpisode : EpisodeDetailsUiEvent
    data class ClickCast(val itemId: Int) : EpisodeDetailsUiEvent
    object ApplyRating : EpisodeDetailsUiEvent

}