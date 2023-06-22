package com.chocolatecake.viewmodel.episode_details

import android.media.Rating
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.mapper.Mapper
import com.chocolatecake.usecase.episode_details.GetCastForEpisodeUseCase
import com.chocolatecake.usecase.episode_details.GetEpisodeDetailsUseCase
import com.chocolatecake.usecase.episode_details.SetEpisodeRatingUseCase
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.search.mappers.PeopleUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodeDetailsViewModel @Inject constructor(
    private val episodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val castUseCase: GetCastForEpisodeUseCase,
    private val setEpisodeRatingUseCase: SetEpisodeRatingUseCase,
    private val peopleUiMapper: PeopleUiMapper,
    private val episodeDetailsUiMapper: EpisodeDetailsUiMapper,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<EpisodeDetailsUiState, EpisodeDetailsUiEvent>(EpisodeDetailsUiState()),
    EpisodeDetailsListener, PeopleListener {

    private val seriesId = savedStateHandle.get<Int>("seriesId") ?: 1772
    private val seasonNumber = savedStateHandle.get<Int>("seasonNumber") ?: 1
    private val episodeNumber = savedStateHandle.get<Int>("episodeNumber") ?: 1

    init {
        getData(seriesId, seasonNumber, episodeNumber)
    }


    private fun getData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        getEpisodeDetailsData(seriesId, seasonNumber, episodeNumber)
        getCastData(seriesId, seasonNumber, episodeNumber)
    }

    private fun getEpisodeDetailsData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        executeEpisodeDetails(
            call = { episodeDetailsUseCase(seriesId, seasonNumber, episodeNumber) },
            mapper = episodeDetailsUiMapper,
            onSuccess = ::onSuccessEpisodeDetail,
            onError = ::onError
        )
    }

    private fun onSuccessEpisodeDetail(episodeDetails: EpisodeDetailsUiState) {
        _state.update {
            it.copy(
                imageUrl = episodeDetails.imageUrl,
                episodeName = episodeDetails.episodeName,
                episodeNumber = episodeDetails.episodeNumber,
                seasonNumber = episodeDetails.seasonNumber,
                episodeRate = episodeDetails.episodeRate,
                episodeOverview = episodeDetails.episodeOverview,
                productionCode = episodeDetails.productionCode,
                isLoading = false,
                onErrors = emptyList()
            )
        }
    }

    fun setRating() {
        viewModelScope.launch {
            try {
                setEpisodeRatingUseCase(
                    seriesId,
                    seasonNumber,
                    episodeNumber,
                    state.value.userRate
                )
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    fun onRating(value: Int) {
        _state.update {
            it.copy(
                userRate = value
            )
        }
    }

    fun getRatingValue(): Float {
        return state.value.userRate.toFloat()
    }

    private fun getCastData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        tryToExecute(
            call = { castUseCase(seriesId, seasonNumber, episodeNumber) },
            mapper = peopleUiMapper,
            onSuccess = ::onSuccessCast,
            onError = ::onError
        )
    }

    private fun onSuccessCast(cast: List<PeopleUIState>) {
        _state.update { it.copy(cast = cast) }
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    private fun <EpisodeDetailsEntity, EpisodeDetailsUiState> executeEpisodeDetails(
        call: suspend () -> EpisodeDetailsEntity,
        onSuccess: (EpisodeDetailsUiState) -> Unit,
        mapper: Mapper<EpisodeDetailsEntity, EpisodeDetailsUiState>,
        onError: (Throwable) -> Unit,
        dispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
        viewModelScope.launch(dispatcher) {
            try {
                mapper.map(call()).also(onSuccess)
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    /// region event
    override fun clickToBack() {
        sendEvent(EpisodeDetailsUiEvent.ClickToBack)
    }

    override fun clickToRate() {
        sendEvent(EpisodeDetailsUiEvent.ClickToRate)
    }

    override fun submitRating() {
        sendEvent(EpisodeDetailsUiEvent.SubmitRating)

    }

    override fun onClickPeople(id: Int) {
        sendEvent(EpisodeDetailsUiEvent.ClickCast(id))
    }

    /// endregion
}