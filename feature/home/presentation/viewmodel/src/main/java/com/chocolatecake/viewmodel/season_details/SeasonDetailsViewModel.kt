package com.chocolatecake.viewmodel.season_details

import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.season_details.SeasonDetailsEntity
import com.chocolatecake.usecase.season_details.GetSeasonDetailsUseCase
import com.chocolatecake.viewmodel.common.listener.EpisodeListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class SeasonDetailsViewModel @Inject constructor(
    private val getSeasonDetailsUseCase: GetSeasonDetailsUseCase,
    private val seasonDetailsUiMapper: SeasonDetailsUiMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<SeasonDetailsUiState, SeasonDetailsUiEvent>(SeasonDetailsUiState()),
    EpisodeListener {

    private val seriesId = savedStateHandle.get<Int>("seriesId") ?: 1396
    private val seasonNumber = savedStateHandle.get<Int>("seasonNumber") ?: 2


    init {
        getData()
    }

     fun getData() {
        _state.update { it.copy(isLoading = true) }
        getSeasonDetails()
    }

    private fun getSeasonDetails() {
        tryToExecute(
            call = { getSeasonDetailsUseCase(seriesId = seriesId, seasonNumber = seasonNumber) },
            onSuccess = ::onSuccessSeasonDetails,
            onError = ::onError,
        )
    }

    private fun onSuccessSeasonDetails(seasonDetailsEntity: SeasonDetailsEntity) {
        _state.update {
            seasonDetailsUiMapper.map(seasonDetailsEntity)
        }
    }

    private fun onError(throwable: Throwable) {
        showErrorWithSnackBar(throwable.message ?: "No Network Connection")
        _state.update {
            it.copy(
                onErrors = listOf(throwable.message ?: "No Network Connection"),
                isLoading = false
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(SeasonDetailsUiEvent.ShowSnackBar(messages))
    }

    override fun onClickEpisode(id: Int) {
        sendEvent(SeasonDetailsUiEvent.NavigateToEpisodeDetails(id))
    }
}