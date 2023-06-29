package com.chocolatecake.viewmodel.watch_history

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.usecase.watch_history.DeleteMovieFromWatchHistoryUseCase
import com.chocolatecake.usecase.watch_history.GetAllWatchHistoryMoviesUseCase
import com.chocolatecake.usecase.watch_history.SearchWatchHistoryUseCase
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.watch_history.mappers.MovieDomainMapper
import com.chocolatecake.viewmodel.watch_history.mappers.MovieUiStateMapper
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    private val getAllWatchHistoryMoviesUseCase: GetAllWatchHistoryMoviesUseCase,
    private val deleteMovieFromWatchHistoryUseCase: DeleteMovieFromWatchHistoryUseCase,
    private val searchWatchHistoryUseCase: SearchWatchHistoryUseCase,
    private val movieDomainMapper: MovieDomainMapper,
    private val movieUiStateMapper: MovieUiStateMapper,
    private val stringsRes:StringsRes
) :
    BaseViewModel<WatchHistoryUiState, WatchHistoryUiEvent>(WatchHistoryUiState()), MediaListener {

    private val itemsCreator = WatchHistoryRecyclerItemsCreator(stringsRes)

    init {
        getAllMovies()
        initSearchCallBacks()
    }

    private fun getAllMovies() {
        updateStateToLoading()
        tryToExecute(
            call = {
                val movies = getAllWatchHistoryMoviesUseCase().map {
                    movieUiStateMapper.map(it)
                }
                itemsCreator.createItems(movies)
            },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onGetAllMoviesError
        )
    }

    private fun onGetAllMoviesSuccess(items: List<WatchHistoryRecyclerItem>) {
        _state.update {
            it.copy(movies = items, isLoading = false)
        }
    }

    private fun onGetAllMoviesError(throwable: Throwable) {
        _state.update { it.copy(isLoading = false) }
        sendErrorEvent(throwable)
    }

    private fun sendErrorEvent(th: Throwable) {
        sendEvent(WatchHistoryUiEvent.Error(th.message.toString()))
    }

    @OptIn(FlowPreview::class)
    private fun initSearchCallBacks() {
        var oldValue = state.value.searchInput
        viewModelScope.launch {
            state.debounce(600)
                .filter { oldValue != state.value.searchInput }
                .collect {
                    onSearchInputChanged(it.searchInput)
                    oldValue = state.value.searchInput
                }
        }
    }

    fun onSearchInputChanged(newSearchInput: CharSequence) {
        _state.update { it.copy(searchInput = newSearchInput.toString(), isLoading = true) }
        viewModelScope.launch {
            tryToSearchInMovies(newSearchInput.toString())
        }
    }

    private suspend fun tryToSearchInMovies(newSearchInput: String) {
        try {
            searchOrGetAllMovies(newSearchInput)
        } catch (th: Throwable) {
            sendErrorEvent(th)
        }
    }

    private suspend fun searchOrGetAllMovies(newSearchInput: String) {
        if (newSearchInput.isEmpty()) {
            getAllMovies()
        } else {
            searchMovies(newSearchInput)
        }
    }

    private suspend fun searchMovies(searchTerm: String) {
        updateStateToLoading()
        val movies = runBlocking {
            searchWatchHistoryUseCase(searchTerm).map {
                movieUiStateMapper.map(it)
            }
        }
        updateStateToSuccess(movies)
    }

    private fun updateStateToLoading() {
        _state.update { it.copy(isLoading = true) }
    }

    private fun updateStateToSuccess(movies: List<MovieUiState>) {
        _state.update { uiState ->
            uiState.copy(
                movies = itemsCreator.createItems(movies),
                isLoading = false
            )
        }
    }

    fun setSearchQuery(query: CharSequence?) {
        _state.update {
            it.copy(searchInput = query.toString())
        }
    }

    override fun onClickMedia(id: Int) {
        sendEvent(WatchHistoryUiEvent.NavigateToMovieDetails(id))
    }

    fun onSnackBarShown() {
        _state.update {
            it.copy(snackBarUndoPressed = false)
        }
    }

    fun setPosition(position: Int) {
        _state.update {
            it.copy(swipePosition = position)
        }
    }

    fun deleteItemFromDataBase() = MainScope().launch {
        runBlocking {
            state.value.deletedMovie?.let { movieUiState ->
                if (state.value.snackBarUndoPressed == false) {
                    deleteMovieFromWatchHistoryUseCase(movieDomainMapper.map(movieUiState))
                }
            }
        }
    }

    fun deleteItemFromUi() {
        val position = state.value.swipePosition
        position?.let {
            if (itemIsNotMovie(position)) return
            deleteItemFromRecyclerList(position)
            sendEvent(WatchHistoryUiEvent.ShowDeleteSnackBar)
            deleteTitleIfDayIsEmpty()
        }
    }

    private fun deleteItemFromRecyclerList(position: Int) {
        val newList = _state.value.movies.toMutableList()
        val tempMovie = newList[position]
        newList.removeAt(position)
        _state.update {
            it.copy(
                movies = newList,
                deletedMovie = (tempMovie as WatchHistoryRecyclerItem.MovieCard).movie
            )
        }
    }

    private fun itemIsNotMovie(position: Int) =
        _state.value.movies[position] !is WatchHistoryRecyclerItem.MovieCard

    private fun deleteTitleIfDayIsEmpty() {
        if (isTitleRedundant()) {
            state.value.swipePosition?.let { pos ->
                removeTitleFromRecyclerList(pos)
            }
        }
    }

    private fun removeTitleFromRecyclerList(position: Int) {
        val newList = _state.value.movies.toMutableList()
        val tempTitle = newList[position - 1]
        newList.removeAt(position - 1)
        _state.update {
            it.copy(
                movies = newList,
                deletedTitle = (tempTitle as WatchHistoryRecyclerItem.Title).title
            )
        }
    }

    private fun isTitleRedundant(): Boolean {
        if (noMoviesExist()) return true
        state.value.swipePosition?.let {
            if (isNoMoviesBetweenTitles(it))
                return true
        }
        return false
    }

    private fun isNoMoviesBetweenTitles(position: Int) =
        (state.value.movies[position - 1] is WatchHistoryRecyclerItem.Title
                && state.value.movies[position] is WatchHistoryRecyclerItem.Title)

    private fun noMoviesExist() = state.value.movies.size <= 2

    fun addItemToUi() {
        val position = state.value.swipePosition
        position?.let {
            val movies = _state.value.movies.toMutableList()
            addDeletedTitle(movies, position)
            addDeletedMovie(movies, position)
            returnOldListToState(movies)
        }

    }

    private fun returnOldListToState(newList: MutableList<WatchHistoryRecyclerItem>) {
        _state.update {
            it.copy(
                movies = newList,
                snackBarUndoPressed = true
            )
        }
    }

    private fun addDeletedMovie(
        newList: MutableList<WatchHistoryRecyclerItem>,
        position: Int
    ) {
        state.value.deletedMovie?.let {
            newList.add(
                position,
                WatchHistoryRecyclerItem.MovieCard(it)
            )
        }
    }

    private fun addDeletedTitle(
        newList: MutableList<WatchHistoryRecyclerItem>,
        position: Int
    ) {
        state.value.deletedTitle?.let {
            newList.add(
                position - 1,
                WatchHistoryRecyclerItem.Title(it)
            )
        }
    }

    fun initTheDeletionStates() {
        _state.update {
            it.copy(
                deletedTitle = null,
                deletedMovie = null,
                snackBarUndoPressed = false
            )
        }
    }

    fun onClickBack(){
        sendEvent(WatchHistoryUiEvent.OnClickBack)
    }
}
