package com.chocolatecake.viewmodel.season_details

import android.util.Log
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.season_details.SeasonDetailsEntity
import com.chocolatecake.usecase.season_details.GetSeasonDetailsUseCase
import com.chocolatecake.viewmodel.common.listener.EpisodeListener
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class SeasonDetailsViewModel @Inject constructor(
    private val getSeasonDetailsUseCase: GetSeasonDetailsUseCase,
    private val seasonDetailsUiMapper: SeasonDetailsUiMapper,
) : BaseViewModel<SeasonDetailsUiState, SeasonDetailsUiEvent>(SeasonDetailsUiState())
    , EpisodeListener {

    init {
        getData()
    }

    private fun getData() {
        _state.update { it.copy(isLoading = true) }
        getSeasonDetails()
    }

    private fun getSeasonDetails() {
        tryToExecute(
            call = {getSeasonDetailsUseCase(seriesId = 1396, seasonNumber = 1)},
            onSuccess = ::onSuccessSeasonDetails,
            onError = ::onError,
        )
    }

    private fun onSuccessSeasonDetails(seasonDetailsEntity: SeasonDetailsEntity){
        Log.e("onSuccess", "onSuccessEpisodes: $seasonDetailsEntity" )
        _state.update {
            seasonDetailsUiMapper.map(seasonDetailsEntity)
        }
    }

    private fun onError(throwable: Throwable){
        showErrorWithSnackBar(throwable.message ?: "No Network Connection")
        _state.update {
            it.copy(
                onErrors = listOf(throwable.message ?: "No Network Connection"),
                isLoading = false
            ) }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(SeasonDetailsUiEvent.ShowSnackBar(messages))
    }

    override fun onClickEpisode(episodeId: Int) {
        sendEvent(SeasonDetailsUiEvent.NavigateToEpisodeDetails(episodeId))
    }
}