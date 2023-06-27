package com.chocolatecake.viewmodel.tv_details

sealed interface TvDetailsUiEvent {
    object Rate : TvDetailsUiEvent
    data class PlayButton(val youtubeKey: String) : TvDetailsUiEvent
    data class ApplyRating(val message: String) : TvDetailsUiEvent
    data class OnPersonClick(val id: Int) : TvDetailsUiEvent
    data class OnSeasonClick(val seasonNumber: Int) : TvDetailsUiEvent
    data class OnRecommended(val id: Int) : TvDetailsUiEvent
    object Back : TvDetailsUiEvent
    object OnShowMoreCast : TvDetailsUiEvent
    object OnShowMoreRecommended : TvDetailsUiEvent
    data class ShowSnackBar(val message: String) : TvDetailsUiEvent
    data class OnSaveButtonClick(val tvShowId: Int) : TvDetailsUiEvent
    data class OnDoneAdding(val message: String):TvDetailsUiEvent
    data class onCreateNewList(val message:String):TvDetailsUiEvent
    class OnFavourite(val message: String) : TvDetailsUiEvent
    class OnWatchList(val message: String) : TvDetailsUiEvent
}
