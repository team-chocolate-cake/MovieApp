package com.chocolatecake.viewmodel.myList

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.myList.FavoriteBodyRequestEntity
import com.chocolatecake.usecase.myList.CreateListUseCase
import com.chocolatecake.usecase.myList.GetListsCreatedUseCase
import com.chocolatecake.usecase.myList.GetMovieListUseCase
import com.chocolatecake.viewmodel.myList.mapper.MyListUiMapper
import com.chocolatecake.viewmodel.myListDetails.MyListDetailsUiEvent
import com.chocolatecake.viewmodel.search.SearchUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMovies: GetListsCreatedUseCase,
    private val myListUiMapper: MyListUiMapper,
    private val createList: CreateListUseCase,
) : BaseViewModel<MyListUiState, MyListUiEvent>(MyListUiState()), MyListListener {


    val newListName = MutableStateFlow("")

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
    }

    private fun onGetAllMoviesError(throwable: Throwable) {
        Log.i("TAG", "onGetAllMoviesError: $throwable")
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


    override fun onClickItem(listId: Int, listType: String, listName: String) {
        sendEvent(
            MyListUiEvent.NavigateToListDetails(
                listId = listId,
                listType = listType,
                listName = listName,
            )
        )
    }

    override fun onClickNewList() {

        tryToExecute(
            call = {
                Log.i("gg", "onCreateList: newwwww ${createList.invoke("mmmmmmnn")} ")
                createList.invoke("mmmmmmnn")
            },
            onSuccess = { Log.i("v2", "onClickNewList:  done $it ") },
            onError = ::onGetAllMoviesError
        )
//        viewModelScope.launch {
//            _event.emit(MyListUiEvent.OpenCreateListBottomSheet)
//        }
//        Log.i("v2", "onClickNewList:  hiiv2 ")
    }

    fun onCreateList() {
        tryToExecute(
            call = {
                Log.i("gg", "onCreateList: ${newListName} ")
                createList.invoke(newListName.toString())
            },
            onSuccess = { Log.i("v2", "onClickNewList:  done $it ") },
            onError = ::onCreateListError
        )
    }

    private fun onCreateListError(throwable: Throwable) {
        Log.i("TAG", "onCreateListError: $throwable")
        _state.update {
            it.copy(
                errors = listOf(
                    Error(
                        2,
                        throwable.message.toString()
                    )
                )
            )
        }
    }

    override fun onClickBackButton() {
        sendEvent(MyListUiEvent.OnClickBack)
    }

}