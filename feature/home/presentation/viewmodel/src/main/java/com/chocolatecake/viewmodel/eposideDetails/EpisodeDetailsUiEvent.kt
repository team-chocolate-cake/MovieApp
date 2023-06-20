package com.chocolatecake.viewmodel.eposideDetails

import com.chocolatecake.viewmodel.home.HomeUiEvent

sealed interface EpisodeDetailsUiEvent {
    object ClickToRate : EpisodeDetailsUiEvent
    object PlayEpisode : EpisodeDetailsUiEvent
    data class ClickCast(val itemId: Int) : EpisodeDetailsUiEvent
    object ApplyRating : EpisodeDetailsUiEvent

}