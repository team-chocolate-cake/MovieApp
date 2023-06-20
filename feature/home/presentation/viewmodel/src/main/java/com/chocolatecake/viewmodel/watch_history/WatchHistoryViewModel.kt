package com.chocolatecake.viewmodel.watch_history

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.MovieInWatchHistoryEntity
import com.chocolatecake.usecase.watch_history.DeleteMovieFromWatchHistoryUseCase
import com.chocolatecake.usecase.watch_history.GetAllWatchHistoryMoviesUseCase
import com.chocolatecake.usecase.watch_history.InsertMovieToWatchHistoryUseCase
import com.chocolatecake.usecase.watch_history.SearchWatchHistoryUseCase
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.watch_history.mappers.MovieDomainMapper
import com.chocolatecake.viewmodel.watch_history.mappers.MovieUiStateMapper
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    private val getAllWatchHistoryMoviesUseCase: GetAllWatchHistoryMoviesUseCase,
    private val deleteMovieFromWatchHistoryUseCase: DeleteMovieFromWatchHistoryUseCase,
    private val searchWatchHistoryUseCase: SearchWatchHistoryUseCase,
    private val movieDomainMapper: MovieDomainMapper,
    private val movieUiStateMapper: MovieUiStateMapper,
    private val insertMovieToWatchHistoryUseCase: InsertMovieToWatchHistoryUseCase
) : BaseViewModel<WatchHistoryUiState, WatchHistoryUiEvent>(WatchHistoryUiState()), MediaListener {

    private val itemsCreator = WatchHistoryRecyclerItemsCreator()

    init {
        getAllMovies()
        initSearchCallBacks()
        testing()
    }
    private fun testing() {
        viewModelScope.launch {
            insertMovieToWatchHistoryUseCase(
                MovieInWatchHistoryEntity(
                    id = 1,
                    posterPath = "https://www.cleveland.com/resizer/4IGudEjrP3cao2OTDbnPW8vAfJI=/arc-anglerfish-arc2-prod-advancelocal/public/S4POABLORVD4HACPBPPHAMOFNQ.jpg",
                    dateWatched = Date(),
                    title = "ronaldo",
                    description = "batata for sale ",
                    voteAverage = 9.3,
                    year = 2012
                )
            )
            insertMovieToWatchHistoryUseCase(
                MovieInWatchHistoryEntity(
                    id = 2,
                    posterPath = "https://www.cleveland.com/resizer/4IGudEjrP3cao2OTDbnPW8vAfJI=/arc-anglerfish-arc2-prod-advancelocal/public/S4POABLORVD4HACPBPPHAMOFNQ.jpg",
                    dateWatched = Date(),
                    title = "messi",
                    description = "batata for sale ",
                    voteAverage = 9.3,
                    year = 2012
                )
            )
            insertMovieToWatchHistoryUseCase(
                MovieInWatchHistoryEntity(
                    id = 3,
                    posterPath = "https://www.cleveland.com/resizer/4IGudEjrP3cao2OTDbnPW8vAfJI=/arc-anglerfish-arc2-prod-advancelocal/public/S4POABLORVD4HACPBPPHAMOFNQ.jpg",
                    dateWatched = Date(),
                    title = "ake",
                    description = "batata for sale ",
                    voteAverage = 9.3,
                    year = 2012
                )
            )
            for (i in 5..20) {
                insertMovieToWatchHistoryUseCase(
                    MovieInWatchHistoryEntity(
                        id = i,
                        posterPath = "https://www.cleveland.com/resizer/4IGudEjrP3cao2OTDbnPW8vAfJI=/arc-anglerfish-arc2-prod-advancelocal/public/S4POABLORVD4HACPBPPHAMOFNQ.jpg",
                        dateWatched = Date(System.currentTimeMillis() - i* 24 * 60 * 60 * 1000),
                        title = "$i",
                        description = "batata for sale ",
                        voteAverage = 9.3,
                        year = 2012
                    )
                )
            }
        }
    }

    private fun getAllMovies() {
        _state.update { it.copy(isLoading = true) }
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
            it.copy(
                movies = items,
                isLoading = false
            )
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
    private fun initSearchCallBacks() = viewModelScope.launch {
        var oldValue = ""
        state.debounce(600)
            .filter { oldValue != state.value.searchInput }
            .collect {
                onSearchInputChanged(it.searchInput)
                oldValue = state.value.searchInput
            }
    }

    private suspend fun onSearchInputChanged(newSearchInput: String) {
        _state.update { it.copy(searchInput = newSearchInput, isLoading = true) }
        viewModelScope.launch {
            try {
                if (newSearchInput.isEmpty()) {
                    getAllMovies()
                } else {
                    searchMovies(newSearchInput)
                }
            } catch (th: Throwable) {
                sendErrorEvent(th)
            }
        }

    }

    private suspend fun searchMovies(searchTerm: String) {
        _state.update { it.copy(isLoading = true) }
        try {
            val movies = runBlocking {
                searchWatchHistoryUseCase(searchTerm).map {
                    movieUiStateMapper.map(it)
                }
            }
            _state.update { uiState ->
                uiState.copy(
                    movies = itemsCreator.createItems(movies),
                    isLoading = false
                )
            }
        } catch (th: Throwable) {
            sendErrorEvent(th)
        }


    }


    fun setSearchQuery(query: CharSequence?) {
        _state.update {
            it.copy(
                searchInput = query.toString()
            )
        }
    }

    override fun onClickMedia(id: Int) {
        sendEvent(
            WatchHistoryUiEvent.NavigateToMovieDetails(id)
        )
    }

    fun onSnackBarShown() {
        _state.update {
            it.copy(
                snackBarUndoPressed = false
            )
        }
    }

    fun setPosition(position: Int) {
        _state.update {
            it.copy(swipePosition = position)
        }
    }

    fun deleteItemFromDataBase() = viewModelScope.launch {
        state.value.deletedMovie?.let { movieUiState ->
            if (state.value.snackBarUndoPressed == false) {
                deleteMovieFromWatchHistoryUseCase(movieDomainMapper.map(movieUiState))
            }
        }
    }

    fun deleteItemFromUi() {
        val position = state.value.swipePosition
        position?.let {
            if (_state.value.movies[position] !is WatchHistoryRecyclerItem.MovieCard) return
            val newList = _state.value.movies.toMutableList()
            val tempMovie = newList[position]
            newList.removeAt(position)
            _state.update {
                it.copy(
                    movies = newList,
                    deletedMovie = (tempMovie as WatchHistoryRecyclerItem.MovieCard).movie
                )
            }
            sendEvent(WatchHistoryUiEvent.ShowDeleteSnackBar)
        }
        deleteTitleIfDayIsEmpty()
    }

    private fun titleNeedsToDelete(): Boolean {
        if (state.value.movies.size <= 2) return true
        val position = state.value.swipePosition
        position?.let {
            if (state.value.movies[position - 1] is WatchHistoryRecyclerItem.Title
                || state.value.movies[position - 1] is WatchHistoryRecyclerItem.Title
            )
                return true
        }

        return false
    }

    private fun deleteTitleIfDayIsEmpty() {
        if (titleNeedsToDelete()) {
            val position = state.value.swipePosition
            position?.let { pos ->
                val newList = _state.value.movies.toMutableList()
                Log.i("batata", "deleteTitleIfDayIsEmpty: ${newList[pos - 1]}")
                val tempTitle = newList[pos - 1]
                newList.removeAt(pos - 1)
                _state.update {
                    it.copy(
                        movies = newList,
                        deletedTitle = (tempTitle as WatchHistoryRecyclerItem.Title).title
                    )
                }
            }

        }
    }

    fun addItemToUi() {
        val position = state.value.swipePosition
        position?.let {
            val newList = _state.value.movies.toMutableList()
            state.value.deletedTitle?.let {
                newList.add(
                    position - 1,
                    WatchHistoryRecyclerItem.Title(it)
                )
            }
            state.value.deletedMovie?.let {
                newList.add(
                    position,
                    WatchHistoryRecyclerItem.MovieCard(it)
                )
            }
            _state.update {
                it.copy(
                    movies = newList,
                    snackBarUndoPressed = true
                )
            }
        }

    }

}
