package com.chocolatecake.viewmodel.tv_details

import android.util.Log
import android.widget.RatingBar
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.CastEntity
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvRatingEntity
import com.chocolatecake.usecase.GetTVDetailsInfoUseCase
import com.chocolatecake.usecase.GetTvDetailsCreditUseCase
import com.chocolatecake.usecase.GetTvDetailsReviewsUseCase
import com.chocolatecake.usecase.GetTvDetailsSeasonsUseCase
import com.chocolatecake.usecase.RateTvShowUseCase
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsCastUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsInfoUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsReviewUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsSeasonMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvRatingUiMapper
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
    private val getTvDetailsReviewsUseCase: GetTvDetailsReviewsUseCase
) : BaseViewModel<TvDetailsUiState, TvDetailsUiEvent>(TvDetailsUiState()), TvDetailsListeners {
    init {
        getData()
    }

    private fun getData() {
        getTvShowInfo()
        getTvShowCast()
        getTvSeasons()
        getTvReviews()
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
        Log.i("Spider", "the state is ${state.value.info.name}")
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
    }

    fun updateRating(ratingBar: RatingBar): Float {
        _state.update {
            it.copy(
                info = it.info.copy(rating = ratingBar.rating.times(2))
            )
        }
        Log.i("rate", "the rating is ${state.value.info.rating}")
        return ratingBar.rating
    }

    fun onRatingSubmit() {
        tryToExecute(
            call = { tvShowUseCase(_state.value.info.rating.toDouble(), 44217) },
            onSuccess = ::onRatingSuccess,
            onError = { Log.i("rate", "something went wrong ${it}") }
        )
    }

    private fun onRatingSuccess(tvRatingEntity: TvRatingEntity) {
        val item = TvRatingUiMapper().map(tvRatingEntity)

        _state.update {
            it.copy(
                ratingSuccess = item.ratingSuccess
            )
        }
        Log.i("rate", "rating was successfull ${state.value.ratingSuccess}")
    }

    private fun getTvShowCast() {
        tryToExecute(
            call = getTvDetailsCreditUseCase::invoke,
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
        Log.i("rate", "cast was successfull ${state.value.cast}")
    }

    private fun getTvSeasons() {
        tryToExecute(
            call = getTvDetailsSeasonsUseCase::invoke,
            onSuccess = ::onTvDetailsSeasonSuccess,
            onError = {}
        )
    }

    private fun onTvDetailsSeasonSuccess(seasons: List<SeasonEntity>) {
        val item = TvDetailsSeasonMapper().map(seasons)
        _state.update {
            it.copy(
                seasons = item.seasons
            )
        }
        Log.i("rate", "seasons ->\n ${state.value.seasons}")
    }
    private fun getTvReviews(){
        tryToExecute(
            call = {getTvDetailsReviewsUseCase()},
            onSuccess = ::onTvDetailsReviewsSuccess,
            onError = {
                Log.i("rate", "review was failed $it")
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
        Log.i("rate", "reviews ->\n ${state.value.reviews}")
    }
    override fun onClickPeople(personId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickMedia(id: Int) {
        TODO("Not yet implemented")
    }


}

