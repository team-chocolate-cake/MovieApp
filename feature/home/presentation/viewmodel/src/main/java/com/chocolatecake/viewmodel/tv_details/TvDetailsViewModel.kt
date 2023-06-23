package com.chocolatecake.viewmodel.tv_details

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.PeopleEntity
import com.chocolatecake.entities.ReviewEntity
import com.chocolatecake.entities.SeasonEntity
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.entities.TvDetailsInfoEntity
import com.chocolatecake.entities.TvShowEntity
import com.chocolatecake.entities.UserListEntity
import com.chocolatecake.entities.YoutubeVideoDetailsEntity
import com.chocolatecake.usecase.AddToUserListUseCase
import com.chocolatecake.usecase.CreateUserListUseCase
import com.chocolatecake.usecase.GetTVDetailsInfoUseCase
import com.chocolatecake.usecase.GetTvDetailsCreditUseCase
import com.chocolatecake.usecase.GetTvDetailsReviewsUseCase
import com.chocolatecake.usecase.GetTvDetailsSeasonsUseCase
import com.chocolatecake.usecase.GetTvShowRecommendationsUseCase
import com.chocolatecake.usecase.GetTvShowYoutubeDetailsUseCase
import com.chocolatecake.usecase.GetUserListsUseCase
import com.chocolatecake.usecase.RateTvShowUseCase
import com.chocolatecake.usecase.movie_details.AddToFavouriteUseCase
import com.chocolatecake.usecase.movie_details.AddToWatchList
import com.chocolatecake.viewmodel.tv_details.listener.TvDetailsListeners
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsCastUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsInfoUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsReviewUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvDetailsSeasonUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvRatingUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvShowUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.TvShowYoutubeVideoDetailsUiMapper
import com.chocolatecake.viewmodel.tv_details.mappers.UserListsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TvDetailsViewModel @Inject constructor(
    private val tvDetailsInfoUiMapper: TvDetailsInfoUiMapper,
    private val tvShowUiMapper: TvShowUiMapper,
    private val tvDetailsInfoUseCase: GetTVDetailsInfoUseCase,
    private val getTvDetailsCreditUseCase: GetTvDetailsCreditUseCase,
    private val getTvDetailsSeasonsUseCase: GetTvDetailsSeasonsUseCase,
    private val tvShowUseCase: RateTvShowUseCase,
    private val getTvDetailsReviewsUseCase: GetTvDetailsReviewsUseCase,
    private val getTvShowRecommendationsUseCase: GetTvShowRecommendationsUseCase,
    private val getTvShowYoutubeDetailsUseCase: GetTvShowYoutubeDetailsUseCase,
    private val getUserListsUseCase: GetUserListsUseCase,
    private val addToUserListUseCase: AddToUserListUseCase,
    private val createUserListUseCase: CreateUserListUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val addToWatchList: AddToWatchList,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<TvDetailsUiState, TvDetailsUiEvent>(TvDetailsUiState()), TvDetailsListeners {
    private val tvShowId =
        savedStateHandle.get<Int>("tvShowId") ?: 44217

    init {
        getData()
    }


    private fun getData() {
        getYoutubeDetails()
        getTvShowInfo()
        getTvShowCast()
        getTvSeasons()
        getTvRecommendations()
        getTvReviews()
    }

    fun emptyUserLists() {
        _state.update {
            it.copy(userLists = emptyList())
        }
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

    //region youtube
    private fun getYoutubeDetails() {
        updateLoading(true)
        tryToExecute(
            call = { getTvShowYoutubeDetailsUseCase(tvShowId) },
            onSuccess = ::onYoutubeDetailsSuccess,
            onError = {
                Log.i("err", "the error $it")
            }
        )
    }

    private fun onYoutubeDetailsSuccess(youtubeVideoEntity: YoutubeVideoDetailsEntity) {
        val item = TvShowYoutubeVideoDetailsUiMapper().map(youtubeVideoEntity)
        _state.update {
            it.copy(
                youtubeKeyId = item.youtubeKeyId
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

    fun addToFavourite() {
        tryToExecute(
            call = { addToFavouriteUseCase(tvShowId) },
            onSuccess = {
                sendEvent(TvDetailsUiEvent.OnFavourite("added successfully"))
            },
            onError = {
                sendEvent(TvDetailsUiEvent.OnFavourite("something went wrong"))
            }
        )
    }
    fun addToWatchlist() {
        tryToExecute(
            call = { addToWatchList(tvShowId) },
            onSuccess = {
                sendEvent(TvDetailsUiEvent.OnWatchList("added successfully"))
            },
            onError = {
                sendEvent(TvDetailsUiEvent.OnWatchList("something went wrong"))
            }
        )
    }
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
        val item = TvDetailsSeasonUiMapper().map(seasons)
        _state.update { it.copy(seasons = item.seasons) }
    }

    //endregion

    //region recommendations
    private fun getTvRecommendations() {
        updateLoading(true)
        tryToExecute(
            call = { getTvShowRecommendationsUseCase(tvShowId) },
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
    fun updateRatingUiState(rate: Float) {
        _state.update {
            it.copy(
                userRating = rate
            )
        }

    }


    fun onRatingSubmit() {
        tryToExecute(
            call = { tvShowUseCase(_state.value.userRating.toDouble(), tvShowId) },
            onSuccess = ::onRatingSuccess,
            onError = {
                Log.i("Click", "${_state.value.userRating.toDouble()}")
                sendEvent(TvDetailsUiEvent.ApplyRating("something went wrong :("))
            }
        )
    }

    private fun onRatingSuccess(statusEntity: StatusEntity) {
        sendEvent(TvDetailsUiEvent.ApplyRating("rating was successful"))
        val item = TvRatingUiMapper().map(statusEntity)
        _state.update {
            it.copy(
                ratingSuccess = item.ratingSuccess
            )
        }
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

    //region user lists
    fun getUserLists() {
        tryToExecute(
            call = { getUserListsUseCase() },
            onSuccess = ::onGetUserListsUseCase,
            onError = {
                Log.i("lists", "something went wrong $it")
            }
        )
    }

    private fun onGetUserListsUseCase(userListsEntity: List<UserListEntity>) {
        val item = UserListsUiMapper().map(userListsEntity)
        _state.update {
            it.copy(
                userLists = item.userLists
            )
        }
        Log.i("lists", "user lists => ${state.value.userLists}")
    }

    fun onDone(listsId: List<Int>) {
        listsId.forEach { id ->
            tryToExecute(
                call = { addToUserListUseCase(id, tvShowId) },
                onSuccess = ::onDoneSuccess,
                onError = {
                    Log.i("chip", "something went wrong")
                }
            )
        }

    }

    private fun onDoneSuccess(statusEntity: StatusEntity) {
        sendEvent(TvDetailsUiEvent.OnDoneAdding("adding was successful"))
    }

    fun createUserNewList(listName: String) {
        tryToExecute(
            call = { createUserListUseCase(listName) },
            onSuccess = ::onCreateUserNewList,
            onError = {
                sendEvent(TvDetailsUiEvent.onCreateNewList("something went wrong"))
            }
        )
    }

    private fun onCreateUserNewList(statusEntity: StatusEntity) {
        sendEvent(TvDetailsUiEvent.onCreateNewList("New List Was Added Successfully"))
        getUserLists()
    }
    //endregion

    //region events
    override fun onRateButtonClick() {
        sendEvent(TvDetailsUiEvent.Rate)
    }

    override fun onClickPeople(id: Int) {
        sendEvent(TvDetailsUiEvent.OnPersonClick(id))
    }

    override fun onClickMedia(id: Int) {
        sendEvent(TvDetailsUiEvent.OnRecommended(id))
    }

    override fun onClickSeason(seasonNumber: Int) {
        sendEvent(TvDetailsUiEvent.OnSeasonClick(seasonNumber))
    }

    override fun onShowMoreCast() {
        sendEvent(TvDetailsUiEvent.OnShowMoreCast)
    }

    override fun onShowMoreRecommended() {
        sendEvent(TvDetailsUiEvent.OnShowMoreRecommended)
    }

    override fun onClickPlayButton() {
        sendEvent(TvDetailsUiEvent.PlayButton(state.value.youtubeKeyId))
    }

    override fun onChipClick(id: Int) {
        val updatedList = state.value.userSelectedLists.toMutableList()
        if (updatedList.remove(id)) Unit else updatedList.add(id)

        _state.update {
            it.copy(
                userSelectedLists = updatedList
            )
        }
        Log.i("chip", "selected lists => ${state.value.userSelectedLists}")
    }

    fun onBack() {
        sendEvent(TvDetailsUiEvent.Back)
    }

    fun onClickSaveButton() {
        sendEvent(TvDetailsUiEvent.OnSaveButtonClick(tvShowId))
    }
    //endregion

    //region util
    private fun onError(th: Throwable) {
        val errors = _state.value.errors.toMutableList()
        errors.add(th.message.toString())
        _state.update { it.copy(errors = errors, isLoading = false) }
    }

    private fun updateLoading(value: Boolean) {
        _state.update { it.copy(isLoading = value) }
    }

    fun refreshScreen() {
        getData()
        _state.update { it.copy(errors = emptyList()) }
    }
    //endregion
}

