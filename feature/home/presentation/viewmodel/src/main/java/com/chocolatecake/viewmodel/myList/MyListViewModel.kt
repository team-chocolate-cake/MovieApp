package com.chocolatecake.viewmodel.myList

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.repository.NoNetworkThrowable
import com.chocolatecake.usecase.myList.CreateListUseCase
import com.chocolatecake.usecase.myList.GetListsCreatedUseCase
import com.chocolatecake.viewmodel.myList.mapper.MyListUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMovies: GetListsCreatedUseCase,
    private val myListUiMapper: MyListUiMapper,
    private val createList: CreateListUseCase,
) : BaseViewModel<MyListUiState, MyListUiEvent>(MyListUiState()), MyListListener {

    init {
        getData()
    }

     fun getData() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovies().map { myListUiMapper.map(it) } },
            onSuccess = ::onGetAllListSuccess,
            onError = ::onError,
        )
    }

    private fun onGetAllListSuccess(items: List<ListMovieUiState>) {
        _state.update {
            it.copy(
                movieList = items,
                isLoading = false,
                error = null,
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
        viewModelScope.launch {
            _event.emit(MyListUiEvent.OpenCreateListBottomSheet)
        }
    }


    fun onCreateList(listName: String) {
        tryToExecute(
            call = {
                createList.invoke(listName)
            },
            onSuccess = ::onCreateUserNewList,
            onError = ::onError,
        )
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
                isLoading = false,
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(MyListUiEvent.ShowSnackBar(messages))
    }


    private fun onCreateUserNewList(item: Boolean) {
        sendEvent(MyListUiEvent.ShowSnackBar("New List Was Added Successfully"))
        getData()
    }


    override fun onClickBackButton() {
        sendEvent(MyListUiEvent.OnClickBack)
    }


}