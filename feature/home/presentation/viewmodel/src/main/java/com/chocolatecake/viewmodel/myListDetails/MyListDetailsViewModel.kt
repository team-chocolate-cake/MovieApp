package com.chocolatecake.viewmodel.myListDetails


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.ListName
import com.chocolatecake.usecase.myList.GetFavoritesByMediaTypeUseCase
import com.chocolatecake.usecase.myList.GetMyListDetailsByListIdUseCase
import com.chocolatecake.usecase.myList.GetWatchlistByMediaTypeUseCase
import com.chocolatecake.viewmodel.myListDetails.mapper.MyListDetailsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyListDetailsViewModel @Inject constructor(
    private val getFavorite: GetFavoritesByMediaTypeUseCase,
    private val getWatchlist: GetWatchlistByMediaTypeUseCase,
    private val getMovieListDetails: GetMyListDetailsByListIdUseCase,
    private val myListDetailsUiMapper: MyListDetailsUiMapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyListDetailsUiState, MyListDetailsUiEvent>(MyListDetailsUiState()),
    MyListDetailsListener {

    private val listType = savedStateHandle.get<String>("listType") ?:""
    private val _listName = savedStateHandle.get<String>("listName") ?:""
        val listName = _listName
    private val listId = savedStateHandle.get<Int>("listId") ?:0

    init {
        when(listName){
            ListName.favorite.name ->{
               getAllFavorite(listType)
           }
            ListName.watchlist.name ->{
                getAllWatchlist(listType)
            }
            else ->{
                getAllMovieListDetails(listId)
            }
        }
    }

    private fun getAllFavorite(listType: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getFavorite(listType).map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onGetAllMoviesError
        )
    }

    private fun getAllWatchlist(listType: String) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getWatchlist(listType).map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onGetAllMoviesError
        )
    }

    private fun getAllMovieListDetails(listId: Int) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovieListDetails(listId).map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onGetAllMoviesError
        )
    }

    private fun onGetAllMoviesSuccess(items: List<MovieUiState>) {
        _state.update {
            it.copy(
                movies = items,
                isLoading = false
            )
        }
        Log.i("jk",items.toString())
    }

    private fun onGetAllMoviesError(throwable: Throwable) {
        _state.update {
            it.copy(
                isLoading = false,
                errors = listOf(
                    Error(
                        2,
                        throwable.message.toString()
                    )
                )
            )
        }
    }

    private fun updateStateToError(e: Exception) {
        _state.update {
            it.copy(
                errors = listOf(
                    Error(
                        code = 1,
                        message = e.message.toString()
                    )
                )
            )
        }
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