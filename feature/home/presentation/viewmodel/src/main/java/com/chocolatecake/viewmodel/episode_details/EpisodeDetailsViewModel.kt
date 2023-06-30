package com.chocolatecake.viewmodel.episode_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.entities.RatingEpisodeDetailsStatusEntity
import com.chocolatecake.usecase.episode_details.GetCastForEpisodeUseCase
import com.chocolatecake.usecase.episode_details.GetEpisodeDetailsUseCase
import com.chocolatecake.usecase.episode_details.GetEpisodeVideoUseCase
import com.chocolatecake.usecase.episode_details.SetEpisodeRatingUseCase
import com.chocolatecake.usecase.movie_details.CheckIsLoginedOrNotUseCase
import com.chocolatecake.viewmodel.common.listener.PeopleListener
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.search.mappers.PeopleUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
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
    private val trailerUiMapper: TrailerUiMapper,
    private val episodeVideoUseCase: GetEpisodeVideoUseCase,
    private val checkIsLoggedInOrNotUseCase: CheckIsLoginedOrNotUseCase,
    savedStateHandle: SavedStateHandle,
    private val stringsRes: StringsRes
) : BaseViewModel<EpisodeDetailsUiState, EpisodeDetailsUiEvent>(EpisodeDetailsUiState()),
    EpisodeDetailsListener, PeopleListener {
    private val seriesId = savedStateHandle.get<Int>("seriesId") ?: 454
    private val seasonNumber = savedStateHandle.get<Int>("seasonNumber") ?: 1
    private val episodeNumber = savedStateHandle.get<Int>("episodeNumber") ?: 1

    init {
        _state.update { it.copy(isLoading = true, isLoggedIn = checkIsLoggedInOrNotUseCase()) }
        getData(seriesId, seasonNumber, episodeNumber)
    }

    private fun getData(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        _state.update { it.copy(isLoading = true) }
        getEpisodeDetailsData(seriesId, seasonNumber, episodeNumber)
        getCastData(seriesId, seasonNumber, episodeNumber)
        getEpisodeVideo(seriesId, seasonNumber, episodeNumber)
    }

    /// region refresh
    fun refresh() {
        _state.update { it.copy(refreshing = true, onErrors = emptyList(), isLoading = true) }
        getData(seriesId, seasonNumber, episodeNumber)
        _state.update { it.copy(refreshing = false, onErrors = emptyList(), isLoading = false) }
    }
    /// endregion

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
                voteAverage = episodeDetails.voteAverage,
                onErrors = emptyList(),
                isLoading = false,
                refreshing = false,
            )
        }
    }
    /// endregion

    ///region video
    private fun getEpisodeVideo(seriesId: Int, seasonNumber: Int, episodeNumber: Int) {
        viewModelScope.launch {
            try {
                val inputData = episodeVideoUseCase(seriesId, seasonNumber, episodeNumber)
                val mappedData = trailerUiMapper.map(inputData)
                onSuccessEpisodeVideo(mappedData)
            } catch (th: Throwable) {
                _state.update { it.copy(trailerKey = "") }
            }
        }
    }

    private fun onSuccessEpisodeVideo(trailerUiState: TrailerUiState) {
        _state.update {
            it.copy(
                trailerKey = trailerUiState.videoKey,
                refreshing = false, onErrors = emptyList()
            )
        }
    }

    /// endregion

    /// region set rating
    fun setRating() {
        tryToExecute(
            call = {
                setEpisodeRatingUseCase(
                    seriesId,
                    seasonNumber,
                    episodeNumber,
                    _state.value.userRate
                )
            },
            onSuccess = ::onRatingSuccess,
            onError = ::onRatingError
        )
    }

    private fun onRatingSuccess(episodeRateSatusEntity: RatingEpisodeDetailsStatusEntity) {
        sendEvent(EpisodeDetailsUiEvent.SubmitRating(stringsRes.ratingAddSuccessFully))
    }

    private fun onRatingError(th: Throwable) {
        sendEvent(EpisodeDetailsUiEvent.SubmitRating(stringsRes.someThingErrorWhenAddRating))
    }

    fun updateRatingState(rate: Float) {
        _state.update { it.copy(userRate = rate, onErrors = emptyList()) }
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
        _state.update { it.copy(cast = cast, refreshing = false, onErrors = emptyList()) }
    }

    /// endregion

    ///  region error

    private fun onError(th: Throwable) {
        val errorMessage = th.message ?: stringsRes.someThingError
        _state.update {
            it.copy(
                onErrors = listOf(errorMessage),
                isLoading = false,
                refreshing = false,
                trailerKey = ""
            )
        }
    }
    /// endregion

    /// region event
    override fun clickToBack() {
        sendEvent(EpisodeDetailsUiEvent.ClickToBack)
    }

    override fun clickToRate(episodeId: Int) {
        sendEvent(EpisodeDetailsUiEvent.ClickToRate(episodeId))
    }

    override fun clickToPlayFullScreen(videoKey: String) {
        sendEvent(EpisodeDetailsUiEvent.ClickToPlayFullScreen(videoKey))
    }

    override fun onClickPeople(id: Int) {
        sendEvent(EpisodeDetailsUiEvent.ClickCast(id))
    }
    /// endregion
}