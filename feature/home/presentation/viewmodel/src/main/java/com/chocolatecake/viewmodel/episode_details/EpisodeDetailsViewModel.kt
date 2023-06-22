package com.chocolatecake.viewmodel.episode_details

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.EpisodeDetailsEntity
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
    private val episodeDetailsUiMapper: EpisodeDetailsUiMapper
) : BaseViewModel<EpisodeDetailsUiState, EpisodeDetailsUiEvent>(EpisodeDetailsUiState()),
    EpisodeDetailsListener, PeopleListener {

    init {
        getData(1772, 1, 1)
    }


    private fun getData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        getEpisodeDetailsData(seriesId, seasonNumber, episodeNumber)
        getCastData(seriesId, seasonNumber, episodeNumber)
    }


    private fun getEpisodeDetailsData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        execute(
            call = { episodeDetailsUseCase(seriesId, seasonNumber, episodeNumber) },
            mapper = episodeDetailsUiMapper,
            onSuccess = ::onSuccessEpisodeDetail,
            onError = ::onError
        )
    }//ههههههههه قول يارب بنشوف

    fun <INPUT, OUTPUT> execute(
        call: suspend () -> INPUT,
        onSuccess: (OUTPUT) -> Unit,
        mapper: Mapper<INPUT, OUTPUT>,
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