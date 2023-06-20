package com.chocolatecake.viewmodel.eposideDetails

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.episodeDetails.GetCastForEpisodeUseCase
import com.chocolatecake.usecase.episodeDetails.GetEpisodeDetailsUseCase
import com.chocolatecake.usecase.episodeDetails.SetEpisodeRatingUseCase
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
    private val episodeDetailsUiMapper: EpisodeDetailsUiMapper
) : BaseViewModel<EpisodeDetailsUiState, EpisodeDetailsUiEvent>(EpisodeDetailsUiState()),
    EpisodeDetailsListener {

    init {
        getData(14784, 1, 1)
    }

    private fun getData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(isLoading = true)
                }
                val episodeDetails = episodeDetailsUseCase(seriesId, seasonNumber, episodeNumber)
                val castItems = castUseCase(seriesId, seasonNumber, episodeNumber)
                    .map { castEntity -> castUiMapper.map(castEntity) }
                _state.update {
                    it.copy(
                        imageUrl = episodeDetails.imageUrl,
                        episodeName = episodeDetails.episodeName,
                        episodeNumber = episodeDetails.episodeNumber,
                        seasonNumber = episodeDetails.seasonNumber,
                        episodeRate = episodeDetails.episodeRate,
                        episodeOverview = episodeDetails.overview,
                        productionCode = episodeDetails.productionCode,
                        cast = castItems,
                        isLoading = false
                    )
                }
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    /// region event
    override fun onClickActor(id: Int) {}

    override fun clickToRate() {}

    override fun playEpisode() {}

    override fun applyRating() {}
    /// endregion
}