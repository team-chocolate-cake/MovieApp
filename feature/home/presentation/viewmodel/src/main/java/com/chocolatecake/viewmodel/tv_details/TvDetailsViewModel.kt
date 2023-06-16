package com.chocolatecake.viewmodel.tv_details

import android.util.Log
import android.widget.RatingBar
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.usecase.GetTVDetailsInfoUseCase
import com.chocolatecake.usecase.RateTvShowUseCase
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsInfoUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvRatingUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    private val tvDetailsInfoUiMapper: TvDetailsInfoUiMapper,
    private val tvDetailsInfoUseCase: GetTVDetailsInfoUseCase,
    private val tvShowUseCase: RateTvShowUseCase
) : BaseViewModel<TvDetailsUiState, TvDetailsUiEvent>(TvDetailsUiState()) {
    init {
        getData()
    }

    private fun getData() {
        getTvShowInfo()
    }

    private fun getTvShowInfo() {

        tryToExecute(
            call = tvDetailsInfoUseCase::invoke,
            onSuccess = ::onSuccessTvShowInfo,
            onError = {
                Log.i("Spider", "the error is $it")
            }
        )

    }

    private fun onSuccessTvShowInfo(tvShowInfoEntity: TvDetailsInfoEntity) {
        Log.i("Spider", "the state is ${state.value.name}")
        val item = tvDetailsInfoUiMapper.map(tvShowInfoEntity)
        _state.update {
            it.copy(
                backdropImageUrl = item.backdropImageUrl,
                name = item.name,
                rating = item.rating,
                description = item.description,
                genres = item.genres
            )
        }
    }

    fun onRateButtonClick() {
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.Rate)
        }
    }

    fun updateRating(ratingBar: RatingBar): Float {
        _state.update {
            it.copy(
                rating = ratingBar.rating.times(2)
            )
        }
        Log.i("rate", "the rating is ${state.value.rating}")
        return ratingBar.rating
    }

    fun onRateApplyButtonClick() {
        viewModelScope.launch {
            _event.emit(TvDetailsUiEvent.ApplyRating)
        }
    }

    fun onRatingSubmit() {
        tryToExecute(
            call = { tvShowUseCase(_state.value.rating.toDouble(), 44217) },
            onSuccess = ::onRatingSuccess,
            onError = { Log.i("rate", "something went wrong ${it}") }
        )
    }

    fun onRatingSuccess(tvRatingEntity: TvRatingEntity) {
        val item = TvRatingUiMapper().map(tvRatingEntity)

        _state.update {
            it.copy(
                ratingSuccess = item.ratingSuccess
            )
        }
        Log.i("rate", "rating was successfull ${state.value.ratingSuccess}")
    }
}

