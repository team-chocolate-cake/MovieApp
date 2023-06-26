package com.chocolatecake.viewmodel.myList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.StatusEntity
import com.chocolatecake.repository.NoNetworkThrowable
import com.chocolatecake.usecase.myList.CreateListUseCase
import com.chocolatecake.usecase.myList.DeleteListUseCase
import com.chocolatecake.usecase.myList.GetListsCreatedUseCase
import com.chocolatecake.viewmodel.myList.mapper.MyListUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(
    private val getMoviesUseCase: GetListsCreatedUseCase,
    private val deleteListUseCase: DeleteListUseCase,
    private val myListUiMapper: MyListUiMapper,
    private val createList: CreateListUseCase,
) : BaseViewModel<MyListUiState, MyListUiEvent>(MyListUiState()), MyListListener {



    init {
        getData()
    }

    fun getData() {
        _state.update { it.copy(isLoading = true ,) }
        tryToExecute(
            call = { getMoviesUseCase().map { myListUiMapper.map(it) } },
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
                isShowDelete = false
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
            onSuccess = ::onCreateUserNewListSuccess,
            onError = ::onError,
        )
    }

    private fun onCreateUserNewListSuccess(item: Boolean) {
        sendEvent(MyListUiEvent.ShowSnackBar("New List Was Added Successfully"))
        getData()
    }



    override fun onClickShowDelete() {
       _state.update { it.copy(isShowDelete = true , error = null  ) }
        Log.i("bb", "onClickShowDelete:  ${_state.value.isShowDelete}  ")
    }

    override fun onClickDelete(listId: Int) {
        tryToExecute(
            call = {
                deleteListUseCase.invoke(listId = listId)
            },
            onSuccess = ::onDeleteListSuccess,
            onError = ::onError,
        )
    }

    private fun onDeleteListSuccess(isDelete: StatusEntity) {
        _state.update { it.copy( isShowDelete = false) }
        getData()
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
                isShowDelete = false
            )
        }
    }


    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(MyListUiEvent.ShowSnackBar(messages))
    }


    override fun onClickBackButton() {
        sendEvent(MyListUiEvent.OnClickBack)
    }

}