package com.chocolatecake.viewmodel.tv_details

import android.util.Log
import android.widget.RatingBar
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
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
import kotlinx.coroutines.launch
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
    private val savedStateHandle: SavedStateHandle
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

    private fun getTvRecommendations() {
        tryToExecute(
            call = { getTvShowRecommendations(tvShowId) },
            onSuccess = ::onTvShowRecommendationsSuccess,
            onError = {}
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

    private fun getTvShowInfo() {
        tryToExecute(
            call = { tvDetailsInfoUseCase(tvShowId) },
            onSuccess = ::onSuccessTvShowInfo,
            onError = {
            }
        )

    }

    private fun onSuccessTvShowInfo(tvShowInfoEntity: TvDetailsInfoEntity) {
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

    override fun onRateButtonClick() {
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.Rate)
        }
        Log.i("Click", "rate button was clicked")
    }

    fun updateRating(ratingBar: RatingBar): Float {
        _state.update {
            it.copy(
                info = it.info.copy(rating = ratingBar.rating.times(2))
            )
        }
        return ratingBar.rating
    }

    fun onRatingSubmit() {
        tryToExecute(
            call = { tvShowUseCase(_state.value.info.rating.toDouble(), 44217) },
            onSuccess = ::onRatingSuccess,
            onError = {
            }
        )
    }

    private fun onRatingSuccess(tvRatingEntity: TvRatingEntity) {
        val item = TvRatingUiMapper().map(tvRatingEntity)

        _state.update {
            it.copy(
                ratingSuccess = item.ratingSuccess
            )
        }
    }

    private fun getTvShowCast() {
        tryToExecute(
            call = { getTvDetailsCreditUseCase(tvShowId) },
            onSuccess = ::onTvDetailsCreditSuccess,
            onError = {}
        )
    }

    private fun onTvDetailsCreditSuccess(castEntity: List<PeopleEntity>) {
        val item = TvDetailsCastUiMapper().map(castEntity)
        _state.update {
            it.copy(
                cast = item.cast
            )
        }
    }

    private fun getTvSeasons() {
        tryToExecute(
            call = { getTvDetailsSeasonsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsSeasonSuccess,
            onError = {}
        )
    }

    private fun onTvDetailsSeasonSuccess(seasons: List<SeasonEntity>) {
        val item = TvDetailsSeasonMapper().map(seasons)
        _state.update { it.copy(seasons = item.seasons) }
    }

    private fun getTvReviews() {
        tryToExecute(
            call = { getTvDetailsReviewsUseCase(tvShowId) },
            onSuccess = ::onTvDetailsReviewsSuccess,
            onError = {
            }
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
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.OnPersonClick(personId))
        }
    }

    override fun onClickMedia(id: Int) {
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.OnRecommended(id))
        }
    }


    override fun onClickSeason(seasonId: Int) {
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.OnSeasonClick(seasonId))
        }
    }

    fun onBack(){
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.Back)
        }
    }
}

