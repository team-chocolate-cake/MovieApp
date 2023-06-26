package com.chocolatecake.viewmodel.youtube_trailer

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.youtube_trailer.GetEpisodeVideoUseCase
import com.chocolatecake.usecase.youtube_trailer.GetMediaTrailerUseCase
import com.chocolatecake.usecase.youtube_trailer.MediaType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject


@HiltViewModel
class TrailerViewModel @Inject constructor(
    private val getMediaTrailerUseCase: GetMediaTrailerUseCase,
    private val getEpisodeVideoUseCase: GetEpisodeVideoUseCase,
    private val trailerUiMapper: TrailerUiMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<YoutubePlayerUIState, TrailerInteraction>(YoutubePlayerUIState()) {

    private val episodeNumber = savedStateHandle.get<Int>("episodeNumber") ?: 1
    private val seasonNumber = savedStateHandle.get<Int>("seasonNumber") ?: 1
    private val seriesId = savedStateHandle.get<Int>("seriesId") ?: 1772

    private val mediaId = savedStateHandle.get<Int>("mediaId") ?: 44217
    private val type = savedStateHandle.get<MediaType>("type") ?: MediaType.MOVIE

    init {
        getData()
    }

    private fun getData() {
        getTrailerVideoForMediaType()
        getTrailerVideoForEpisode()
    }

    private fun getTrailerVideoForMediaType() {
        executeEpisodeDetails(
            call = { getMediaTrailerUseCase(type, mediaId) },
            mapper = trailerUiMapper,
            onSuccess = ::onSuccessTrailer,
            onError = ::onError
        )
    }

    private fun getTrailerVideoForEpisode() {
        executeEpisodeDetails(
            call = {
                getEpisodeVideoUseCase(
                    seriesId,
                    seasonNumber,
                    episodeNumber,
                )
            },
            mapper = trailerUiMapper,
            onSuccess = ::onSuccessTrailer,
            onError = ::onError
        )
    }

    private fun onSuccessTrailer(trailerUiState: TrailerUiState) {
        _state.update {
            it.copy(
                videoKey = trailerUiState.videoKey
            )
        }
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.errors?.toMutableList()
        errors?.add(th.message.toString())
        _state.update { it.copy(errors = errors, isLoading = false) }
    }
}