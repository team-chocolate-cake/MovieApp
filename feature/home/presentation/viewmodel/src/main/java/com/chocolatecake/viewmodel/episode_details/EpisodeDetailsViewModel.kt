package com.chocolatecake.viewmodel.episode_details

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.episode_details.GetCastForEpisodeUseCase
import com.chocolatecake.usecase.episode_details.GetEpisodeDetailsUseCase
import com.chocolatecake.usecase.episode_details.SetEpisodeRatingUseCase
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val castUseCase: GetCastForEpisodeUseCase,
    private val setEpisodeRatingUseCase: SetEpisodeRatingUseCase,
    private val castUiMapper: CastUiMapper,
) : BaseViewModel<EpisodeDetailsUiState, EpisodeDetailsUiEvent>(EpisodeDetailsUiState()),
    EpisodeDetailsListener, PeopleListener {

    init {
        getData(1772, 1, 1)
    }

    private fun getData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        viewModelScope.launch {
            try {
                val episodeDetails = episodeDetailsUseCase(seriesId, seasonNumber, episodeNumber)
                val castItems = castUseCase(seriesId, seasonNumber, episodeNumber)
                    .map { castEntity -> castUiMapper.map(castEntity) }
                val newState = _state.value.copy(
                    imageUrl = episodeDetails.imageUrl,
                    episodeName = episodeDetails.episodeName,
                    episodeNumber = episodeDetails.episodeNumber,
                    seasonNumber = episodeDetails.seasonNumber,
                    episodeRate = episodeDetails.episodeRate,
                    episodeOverview = episodeDetails.overview,
                    productionCode = episodeDetails.productionCode,
                    cast = castItems,
                    isLoading = false,
                    onErrors = emptyList()
                )
                _state.update { newState }
                checkIfTheProductionCodeIsEmpty(newState.productionCode)
                Log.d("banan-data-viewmodel", newState.cast.toString())
            } catch (th: Throwable) {
                onError(th)
                Log.d("banan-error", th.message.toString())
            }
        }
    }

    private fun checkIfTheProductionCodeIsEmpty(productionCode: String) {
        if (productionCode.isEmpty()) {
            Log.e("banan","empty")
        }else
        Log.e("banan","not empty")

    }

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    fun submitRating() {

    }

    /// region event

    override fun clickToRate() {
        sendEvent(EpisodeDetailsUiEvent.ClickToRate)
        Log.i("Click", "rate button was clicked")
    }

    override fun playEpisode() {}

    override fun applyRating() {}
    override fun onClickPeople(id: Int) {}
    /// endregion
}