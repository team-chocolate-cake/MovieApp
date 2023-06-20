
package com.chocolatecake.viewmodel.myList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.myList.GetMovieListUseCase
import com.chocolatecake.viewmodel.myList.mapper.MyListUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMovies: GetMovieListUseCase,
    private val myListUiMapper: MyListUiMapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyListUiState, MyListUiEvent>(MyListUiState()), MyListListener {

    private val mediaId = savedStateHandle.get<Int>("mediaId") ?:0
    private val mediaType = savedStateHandle.get<String>("mediaType") ?:""
    private val listId = savedStateHandle.get<Int>("listId") ?:0

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovies().map { myListUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onGetAllMoviesError
        )
    }

    private fun onGetAllMoviesSuccess(items: List<ListMovieUiState>) {
        _state.update {
            it.copy(
                movieList = items,
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

    override fun onClickFavoriteList(itemId: Int, listType: String) {
        sendEvent(
            MyListUiEvent.NavigateToListDetails(listId= listId, listType= listType)
        )
    }

    override fun onClickWatchlist(itemId: Int, listType: String) {
        sendEvent(
            MyListUiEvent.NavigateToListDetails(listId= listId, listType= listType)
        )
    }

    override fun onClickItem(listId: Int, listType: String) {
        sendEvent(
            MyListUiEvent.NavigateToListDetails(listId= listId, listType= listType)
        )
    }

}