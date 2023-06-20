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
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<TvDetailsUiState, TvDetailsUiEvent>(TvDetailsUiState()), TvDetailsListeners {
    var rate = 0.0f
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
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { tvDetailsInfoUseCase(tvShowId) },
            onSuccess = ::onSuccessTvShowInfo,
            onError = ::onError
        )
    }

    private fun onSuccessTvShowInfo(tvShowInfoEntity: TvDetailsInfoEntity) {
        _state.update { it.copy(isLoading = false) }
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
        tryToExecute(
            call = { getTvDetailsCreditUseCase(tvShowId) },
            onSuccess = ::onTvDetailsCastSuccess,
            onError = ::onError
        )
    }


    private fun onTvDetailsCastSuccess(castEntity: List<PeopleEntity>) {
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
        tryToExecute(
            call = { getTvDetailsSeasonsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsSeasonSuccess,
            onError = ::onError
        )
    }

    private fun onTvDetailsSeasonSuccess(seasons: List<SeasonEntity>) {
        val item = TvDetailsSeasonMapper().map(seasons)
        _state.update { it.copy(seasons = item.seasons) }
    }
    //endregion
    private fun getTvRecommendations() {
        tryToExecute(
            call = { getTvShowRecommendations(tvShowId) },
            onSuccess = ::onTvShowRecommendationsSuccess,
            onError = ::onError
        )
    }


    private fun onTvShowRecommendationsSuccess(recommendations: List<TvShowEntity>) {
        val item = tvShowUiMapper.map(recommendations)
        _state.update {
            it.copy(
                recommended = item.recommended
            )
        }
    }


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
        Log.i("Click", "${state.value.userRating}")
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



    private fun getTvReviews() {
        tryToExecute(
            call = { getTvDetailsReviewsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsReviewsSuccess,
            onError = ::onError
        )
    }

    private fun onTvDetailsReviewsSuccess(seasons: List<ReviewEntity>) {
        val item = TvDetailsReviewUiMapper().map(seasons)
        _state.update {
            it.copy(
                reviews = item
            )
        }
    }

    override fun onClickPeople(personId: Int) {
        sendEvent(TvDetailsUiEvent.OnPersonClick(personId))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(TvDetailsUiEvent.OnRecommended(id))
    }


    override fun onClickSeason(seasonId: Int) {
        sendEvent(TvDetailsUiEvent.OnSeasonClick(seasonId))
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

    private fun onError(th: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }
}

