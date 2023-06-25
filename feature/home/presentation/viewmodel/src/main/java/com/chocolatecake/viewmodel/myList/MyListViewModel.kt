package com.chocolatecake.viewmodel.myList

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.myList.CreateListUseCase
import com.chocolatecake.usecase.myList.GetListsCreatedUseCase
import com.chocolatecake.usecase.myList.MakeAsFavoriteUseCase
import com.chocolatecake.viewmodel.myList.mapper.MyListUiMapper
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
        getAllList()
    }

    private fun getAllList() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovies().map { myListUiMapper.map(it) } },
            onSuccess = ::onGetAllListSuccess,
            onError = ::onGetAllListError
        )
    }

    private fun onGetAllListSuccess(items: List<ListMovieUiState>) {
        _state.update {
            it.copy(
                movieList = items,
                isLoading = false
            )
        }
    }

    private fun onGetAllListError(throwable: Throwable) {
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
        sendEvent(MyListUiEvent.ShowSnackBar("something went wrong"))
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
            onError = ::onCreateUserNewListError
        )
    }

    private fun onCreateUserNewList(item: Boolean) {
        sendEvent(MyListUiEvent.ShowSnackBar("New List Was Added Successfully"))
        getAllList()
    }

    private fun onCreateUserNewListError(throwable: Throwable) {
        sendEvent(MyListUiEvent.OnCreateNewList("something went wrong"))
    }

    override fun onClickBackButton() {
        sendEvent(MyListUiEvent.OnClickBack)
    }


}