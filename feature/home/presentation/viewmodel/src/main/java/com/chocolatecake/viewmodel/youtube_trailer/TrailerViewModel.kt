package com.chocolatecake.viewmodel.youtube_trailer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrailerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<YoutubePlayerUIState, TrailerInteraction>(YoutubePlayerUIState()) {

    private val videoKey =
        savedStateHandle.get<String>("videoKey") ?: "GGE35fYuV1A"

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try { _state.update {
                it.copy(videoKey = videoKey, isLoading = false, errors = emptyList())
                }
            } catch (th: Throwable) {
                _state.update { it.copy(isLoading = false, errors = listOf(th.message.toString())) }
            }
        }
    }
}