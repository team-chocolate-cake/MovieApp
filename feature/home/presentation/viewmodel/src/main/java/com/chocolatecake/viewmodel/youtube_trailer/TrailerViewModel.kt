package com.chocolatecake.viewmodel.youtube_trailer

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.youtube_trailer.GetMediaTrailerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class TrailerViewModel @Inject constructor(
    private val getMediaTrailerUseCase: GetMediaTrailerUseCase,
    private val mediaTrailerUseCase: GetMediaTrailerUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<YoutubePlayerUIState, TrailerInteraction>(YoutubePlayerUIState()) {

    private val mediaID  = savedStateHandle.get<Int>("mediaID") ?: 1

    init {

    }

    private fun getData() {

    }

}