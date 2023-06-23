package com.chocolatecake.viewmodel.episode_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.RatingEpisodeDetailsStatusEntity
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
    private val setEpisodeRatingUseCase: SetEpisodeRatingUseCase,
    private val episodeDetailsUseCase: GetEpisodeDetailsUseCase,
    private val episodeDetailsUiMapper: EpisodeDetailsUiMapper,
    private val castUseCase: GetCastForEpisodeUseCase,
    private val peopleUiMapper: PeopleUiMapper,
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
        _state.update { it.copy(isLoading = true) }
        getEpisodeDetailsData(seriesId, seasonNumber, episodeNumber)
        getCastData(seriesId, seasonNumber, episodeNumber)
    }
    fun refresh() {
        _state.value = EpisodeDetailsUiState(refreshing = true)
        getData(seriesId, episodeNumber, episodeNumber)
        _state.value = EpisodeDetailsUiState(refreshing = false)
    }

    /// region episode data
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
    /// endregion

    /// region set rating
    fun setRating() {
        tryToExecute(
            call = {
                setEpisodeRatingUseCase(
                    seriesId, seasonNumber, episodeNumber, _state.value.userRate
                )
            }, onSuccess = ::onRatingSuccess, onError = ::onRatingError
        )
    }

    private fun onRatingSuccess(episodeRateStatusEntity: RatingEpisodeDetailsStatusEntity) {
        sendEvent(EpisodeDetailsUiEvent.SubmitRating("rating was successfully :)"))
    }

    private fun onRatingError(th: Throwable) {
        sendEvent(EpisodeDetailsUiEvent.SubmitRating("rating was failed :("))
    }

    fun updateRatingState(rate: Float) {
        _state.update {
            it.copy(
                userRate = rate
            )
        }
    }

    /// endregion

    /// region cast data
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

    /// endregion

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    /// region event
    override fun clickToBack() {
        sendEvent(EpisodeDetailsUiEvent.ClickToBack)
    }

    override fun clickToRate(episodeId: Int) {
        sendEvent(EpisodeDetailsUiEvent.ClickToRate(episodeId))
    }

    override fun onClickPeople(id: Int) {
        sendEvent(EpisodeDetailsUiEvent.ClickCast(id))
    }

    /// endregion
}