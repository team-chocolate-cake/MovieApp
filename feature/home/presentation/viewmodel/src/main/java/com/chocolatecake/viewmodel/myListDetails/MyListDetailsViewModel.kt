package com.chocolatecake.viewmodel.myListDetails


import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.myList.GetMyFavoriteListUseCase
import com.chocolatecake.usecase.myList.GetMyListDetailsByListIdUseCase
import com.chocolatecake.viewmodel.myListDetails.mapper.MyListDetailsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MyListDetailsViewModel @Inject constructor(

    private val getMoviesFavorite: GetMyFavoriteListUseCase,
//    private val getTvFavorite: GetMyFavoriteTvListUseCase,
    private val getMovies: GetMyListDetailsByListIdUseCase,
    private val myListDetailsUiMapper: MyListDetailsUiMapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyListDetailsUiState, MyListDetailsUiEvent>(MyListDetailsUiState()),
    MyListDetailsListener {


    private val mediaId = savedStateHandle.get<Int>("mediaId") ?:0
    private val mediaType = savedStateHandle.get<String>("mediaType") ?:""
    private val listId = savedStateHandle.get<Int>("listId") ?:0

    init {
        getAllFavoriteMovies()
    }

    private fun getAllFavoriteMovies() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMoviesFavorite().map { myListDetailsUiMapper.map(it) } },
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

}