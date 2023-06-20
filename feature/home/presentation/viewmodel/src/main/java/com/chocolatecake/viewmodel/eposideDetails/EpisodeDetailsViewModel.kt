package com.chocolatecake.viewmodel.eposideDetails

import android.util.Log
import android.widget.RatingBar
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.episodeDetails.GetCastForEpisodeUseCase
import com.chocolatecake.usecase.episodeDetails.GetEpisodeDetailsUseCase
import com.chocolatecake.usecase.episodeDetails.SetEpisodeRatingUseCase
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
                _state.update { currentState ->
                    currentState.copy(
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
                }
                Log.d("banan-data-viewmodel", _state.value.cast.toString())
            } catch (th: Throwable) {
                onError(th)
                Log.d("banan-error", th.message.toString())
            }
        }
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