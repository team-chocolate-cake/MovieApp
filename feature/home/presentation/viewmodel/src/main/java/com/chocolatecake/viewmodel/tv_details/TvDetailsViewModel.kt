package com.chocolatecake.viewmodel.tv_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.usecase.GetTVDetailsInfoUseCase
import com.chocolatecake.usecase.GetTvDetailsCreditUseCase
import com.chocolatecake.usecase.GetTvDetailsReviewsUseCase
import com.chocolatecake.usecase.GetTvDetailsSeasonsUseCase
import com.chocolatecake.usecase.GetTvShowRecommendations
import com.chocolatecake.usecase.RateTvShowUseCase
import com.chocolatecake.viewmodel.tv_details.listener.TvDetailsListeners
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsCastUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsInfoUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsReviewUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsSeasonMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvRatingUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvShowUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    private val tvDetailsInfoUiMapper: TvDetailsInfoUiMapper,
    private val tvDetailsInfoUseCase: GetTVDetailsInfoUseCase,
    private val getTvDetailsCreditUseCase: GetTvDetailsCreditUseCase,
    private val getTvDetailsSeasonsUseCase: GetTvDetailsSeasonsUseCase,
    private val tvShowUseCase: RateTvShowUseCase,
    private val getTvDetailsReviewsUseCase: GetTvDetailsReviewsUseCase,
    private val getTvShowRecommendations: GetTvShowRecommendations,
    private val tvShowUiMapper: TvShowUiMapper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TvDetailsUiState, TvDetailsUiEvent>(TvDetailsUiState()), TvDetailsListeners {
    private val tvShowId =
        savedStateHandle.get<Int>("tvShowId") ?: 44217

    init {
        getData()
    }

    private fun getData() {
        getTvShowInfo()
        getTvShowCast()
        getTvSeasons()
        getTvRecommendations()
        getTvReviews()
    }

    //region info
    private fun getTvShowInfo() {
        updateLoading(true)
        tryToExecute(
            call = { tvDetailsInfoUseCase(tvShowId) },
            onSuccess = ::onSuccessTvShowInfo,
            onError = ::onError
        )
    }

    private fun onSuccessTvShowInfo(tvShowInfoEntity: TvDetailsInfoEntity) {
        updateLoading(false)
        val item = tvDetailsInfoUiMapper.map(tvShowInfoEntity)
        _state.update {
            it.copy(
                info = it.info.copy(
                    backdropImageUrl = item.info.backdropImageUrl,
                    name = item.info.name,
                    rating = item.info.rating,
                    description = item.info.description,
                    genres = item.info.genres
                ),
            )
        }
    }

    //endregion

    //region cast
    private fun getTvShowCast() {
        updateLoading(true)
        tryToExecute(
            call = { getTvDetailsCreditUseCase(tvShowId) },
            onSuccess = ::onTvDetailsCastSuccess,
            onError = ::onError
        )
    }


    private fun onTvDetailsCastSuccess(castEntity: List<PeopleEntity>) {
        updateLoading(false)
        val item = TvDetailsCastUiMapper().map(castEntity)
        _state.update {
            it.copy(
                cast = item.cast
            )
        }
    }

    //endregion

    //region seasons
    private fun getTvSeasons() {
        updateLoading(true)
        tryToExecute(
            call = { getTvDetailsSeasonsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsSeasonSuccess,
            onError = ::onError
        )
    }

    private fun onTvDetailsSeasonSuccess(seasons: List<SeasonEntity>) {
        updateLoading(false)
        val item = TvDetailsSeasonMapper().map(seasons)
        _state.update { it.copy(seasons = item.seasons) }
    }

    //endregion

    //region recommendations
    private fun getTvRecommendations() {
        updateLoading(true)
        tryToExecute(
            call = { getTvShowRecommendations(tvShowId) },
            onSuccess = ::onTvShowRecommendationsSuccess,
            onError = ::onError
        )
    }


    private fun onTvShowRecommendationsSuccess(recommendations: List<TvShowEntity>) {
        updateLoading(false)
        val item = tvShowUiMapper.map(recommendations)
        _state.update {
            it.copy(
                recommended = item.recommended
            )
        }
    }
    //endregion

    //region rating
    override fun onRateButtonClick() {
        sendEvent(TvDetailsUiEvent.Rate)
    }

    fun passRatingValue(rate: Float) {
        updateRatingState(rate)
    }

    private fun updateRatingState(rate: Float) {
        _state.update {
            it.copy(
                userRating = rate
            )
        }
    }

    fun onRatingSubmit() {
        tryToExecute(
            call = { tvShowUseCase(_state.value.userRating.toDouble(), 44217) },
            onSuccess = ::onRatingSuccess,
            onError = {
                Log.i("Click", "${_state.value.userRating.toDouble()}")
                onApplyRating("something went wrong :(")
            }
        )
    }

    private fun onRatingSuccess(tvRatingEntity: TvRatingEntity) {
        onApplyRating("rating was successful")
        val item = TvRatingUiMapper().map(tvRatingEntity)
        _state.update {
            it.copy(
                ratingSuccess = item.ratingSuccess
            )
        }
    }

    private fun onApplyRating(message: String) {
        sendEvent(TvDetailsUiEvent.ApplyRating(message))
    }
    //endregion

    //region reviews
    private fun getTvReviews() {
        updateLoading(true)
        tryToExecute(
            call = { getTvDetailsReviewsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsReviewsSuccess,
            onError = ::onError
        )
    }

    private fun onTvDetailsReviewsSuccess(seasons: List<ReviewEntity>) {
        updateLoading(false)
        val item = TvDetailsReviewUiMapper().map(seasons)
        _state.update {
            it.copy(
                reviews = item
            )
        }
    }
    //endregion

    //region events
    override fun onClickPeople(id: Int) {
        sendEvent(TvDetailsUiEvent.OnPersonClick(id))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(TvDetailsUiEvent.OnRecommended(id))
    }

    override fun onClickSeason(id: Int) {
        sendEvent(TvDetailsUiEvent.OnSeasonClick(id))
    }

    override fun onShowMoreCast() {
        sendEvent(TvDetailsUiEvent.OnShowMoreCast)
    }

    override fun onShowMoreRecommended() {
        sendEvent(TvDetailsUiEvent.OnShowMoreRecommended)
    }

    fun onBack() {
        sendEvent(TvDetailsUiEvent.Back)
    }
    //endregion

    //region util functions
    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    private fun updateLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }
    //endregion
}

