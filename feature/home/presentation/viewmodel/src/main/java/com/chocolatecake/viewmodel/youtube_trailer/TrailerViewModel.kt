package com.chocolatecake.viewmodel.youtube_trailer

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<YoutubePlayerUIState, TrailerInteraction>(YoutubePlayerUIState()) {

    private val videoKey =
        savedStateHandle.get<String>("videoKey") ?: ""

    init {
        getData()
    }

    private fun getData() {
        try {
            _state.update { it.copy(videoKey = videoKey, isLoading = false, errors = emptyList()) }
        } catch (th: Throwable) {
            _state.update { it.copy(isLoading = false, errors = listOf(th.message.toString())) }
        }
    }
}