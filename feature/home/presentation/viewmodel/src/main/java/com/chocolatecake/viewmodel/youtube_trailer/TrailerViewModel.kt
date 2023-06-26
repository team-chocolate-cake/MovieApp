package com.chocolatecake.viewmodel.youtube_trailer

import android.util.Log
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
    savedStateHandle: SavedStateHandle
) : BaseViewModel<YoutubePlayerUIState, TrailerInteraction>(YoutubePlayerUIState()) {

    private val videoKey =
        savedStateHandle.get<String>("videoKey") ?: "JU_FTUQ1iLI"

    init {
        getData()
    }
    private fun getData() {
        _state.update { it.copy(videoKey = videoKey) }
         Log.d("videoKey",videoKey)
    }

  /*  private val getMediaTrailerUseCase: GetMediaTrailerUseCase,
    private val getEpisodeVideoUseCase: GetEpisodeVideoUseCase,
    private val trailerUiMapper: TrailerUiMapper,*/

    //getTrailerVideoForMediaType()
    // getTrailerVideoForEpisode()

    /* private fun getTrailerVideoForMediaType() {
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
     }*/

}