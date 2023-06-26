package com.chocolatecake.viewmodel.myListDetails


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.ListName
import com.chocolatecake.bases.ListType
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.NoNetworkThrowable
import com.chocolatecake.usecase.movie_details.AddToFavouriteUseCase
import com.chocolatecake.usecase.movie_details.AddToWatchList
import com.chocolatecake.usecase.myList.DeleteMovieFromDetailsListUseCase
import com.chocolatecake.usecase.myList.GetMyFavoriteListUseCase
import com.chocolatecake.usecase.myList.GetMyListDetailsByListIdUseCase
import com.chocolatecake.usecase.myList.GetMyWatchlistListUseCase
import com.chocolatecake.usecase.myList.MakeAsFavoriteUseCase
import com.chocolatecake.usecase.myList.MakeAsWatchlistUseCase
import com.chocolatecake.viewmodel.movieDetails.MovieDetailsUiEvent
import com.chocolatecake.viewmodel.myListDetails.mapper.MyListDetailsUiMapper
import com.chocolatecake.viewmodel.search.SearchUiEvent
import com.chocolatecake.viewmodel.search.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.net.SocketTimeoutException
import javax.inject.Inject
import kotlin.Error

@HiltViewModel
class MyListDetailsViewModel @Inject constructor(
    private val getFavoriteUseCase: GetMyFavoriteListUseCase,
    private val getWatchlistUseCase: GetMyWatchlistListUseCase,
    private val getMovieListDetailsUseCase: GetMyListDetailsByListIdUseCase,
    private val deleteFavoriteUseCase: AddToFavouriteUseCase,
    private val deleteMovieFromDetailsListUseCase: DeleteMovieFromDetailsListUseCase,
    private val deleteWatchlistUseCase: AddToWatchList,
    private val myListDetailsUiMapper: MyListDetailsUiMapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyListDetailsUiState, MyListDetailsUiEvent>(MyListDetailsUiState()),
    MyListDetailsListener {

    private val listType = savedStateHandle.get<String>("listType") ?: ""
    private val _listName = savedStateHandle.get<String>("listName") ?: ""
    val listName = _listName
    private val listId = savedStateHandle.get<Int>("listId") ?: 0

    init {
        getData()
    }

    fun getData() {
        when (listName) {
            ListName.favorite.name -> {
                getAllFavorite()
            }

            ListName.watchlist.name -> {
                getAllWatchlist()
            }

            else -> {
                getAllMovieListDetails(listId)
            }
        }
    }

    private fun getAllFavorite() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getFavoriteUseCase().map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun getAllWatchlist() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getWatchlistUseCase().map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun getAllMovieListDetails(listId: Int) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovieListDetailsUseCase(listId).map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun onGetAllMoviesSuccess(items: List<MovieUiState>) {
        _state.update {
            it.copy(
                movies = items,
                isLoading = false,
                error = null,
            )
        }
    }


    fun deleteMedia(position: Int) {

        val mediaId = state.value.movies[position].id
        _state.update {
            it.copy(
                isLoading = true,
            )
        }
        when (listName) {
            ListName.favorite.name -> {
                deleteFavorite(mediaId)
            }

            ListName.watchlist.name -> {
                deleteWatchlist(mediaId)
            }

            else-> {
                deleteMovieFromListDetails(mediaId)
            }
        }
    }

    private fun deleteFavorite(mediaId: Int) {
        tryToExecute(
            call = { deleteFavoriteUseCase(mediaId, "movie", false) },
            onSuccess = ::onDeleteMediaSuccess ,
            onError = ::onError,
        )
    }

    private fun deleteWatchlist(mediaId: Int) {
        tryToExecute(
            call = { deleteWatchlistUseCase(mediaId, "movie", false) },
            onSuccess = ::onDeleteMediaSuccess ,
            onError = ::onError,
        )
    }

    private fun deleteMovieFromListDetails(mediaId: Int) {
        tryToExecute(
            call = {
                deleteMovieFromDetailsListUseCase(listId = listId, mediaId = mediaId)
            },
            onSuccess =  ::onDeleteMediaSuccess ,
            onError = ::onError,
        )
    }


    private fun onDeleteMediaSuccess(isDelete: StatusEntity ) {
        _state.update { it.copy(isLoading = false) }
        when (listName) {
            ListName.favorite.name -> {
                getAllFavorite()
            }

            ListName.watchlist.name -> {
                getAllWatchlist()
            }

            else -> {
                getAllMovieListDetails(listId)
            }
        }
    }


    private fun onError(throwable: Throwable) {
        if (throwable == NoNetworkThrowable()) {
            showErrorWithSnackBar(throwable.message ?: "No Network Connection")
        } else if (throwable == SocketTimeoutException()) {
            showErrorWithSnackBar(throwable.message ?: "time out!")
        }
        _state.update {
            it.copy(
                error = listOf(throwable.message ?: "No Network Connection"),
                isLoading = false
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(MyListDetailsUiEvent.ShowSnackBar(messages))
    }


    override fun onClickItem(itemId: Int) {
        sendEvent(
            MyListDetailsUiEvent.NavigateToMovieDetails(itemId)
        )
    }

    override fun onClickBackButton() {
        sendEvent(MyListDetailsUiEvent.OnClickBack)
    }

}