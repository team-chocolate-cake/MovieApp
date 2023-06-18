package com.chocolatecake.viewmodel.watch_history

import android.graphics.Movie
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
import com.chocolatecake.viewmodel.watch_history.state_managment.MovieUiState
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryOnEventListeners
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiEvent
import com.chocolatecake.viewmodel.watch_history.state_managment.WatchHistoryUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.chocolatecake.viewmodel.watch_history.state_managment.Error
import kotlinx.coroutines.runBlocking
import java.util.Date
import kotlin.math.abs

@HiltViewModel
class WatchHistoryViewModel @Inject constructor(
    private val getAllWatchHistoryMoviesUseCase: GetAllWatchHistoryMoviesUseCase,
    private val deleteMovieFromWatchHistoryUseCase: DeleteMovieFromWatchHistoryUseCase,
    private val searchWatchHistoryUseCase: SearchWatchHistoryUseCase,
    private val movieDomainMapper: MovieDomainMapper,
    private val insertMovieToWatchHistoryUseCase: InsertMovieToWatchHistoryUseCase,
    private val movieUiStateMapper: MovieUiStateMapper
) : BaseViewModel<WatchHistoryUiState, WatchHistoryUiEvent>(WatchHistoryUiState()), MediaListener {
    init {
        getAllMovies()
        initSearchCallBacks()
//        testing()
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
                        dateWatched = Date(),
                        title = "ronaldo",
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
                getAllWatchHistoryMoviesUseCase().map {
                    movieUiStateMapper.map(it)
                }
            },
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
            } catch (e: Exception) {
                updateStateToError(e)
            }
        }

    }

    private suspend fun searchMovies(searchTerm: String) {
        var result: List<MovieUiState>
        runBlocking {
            result = searchWatchHistoryUseCase(searchTerm).map {
                movieUiStateMapper.map(it)
            }
        }

        _state.update { uiState ->
            uiState.copy(
                movies = result,
                isLoading = false
            )
        }
    }

    fun setSearchQuery(query: CharSequence?) {
        _state.update { it.copy(searchInput = query.toString()) }
    }

    override fun onClickMedia(id: Int) {
        sendEvent(
            WatchHistoryUiEvent.NavigateToMovieDetails(id)
        )
    }

    fun deleteItemFromDataBase() {
        viewModelScope.launch {
            state.value.tempMovie?.let {
                deleteMovieFromWatchHistoryUseCase(movieDomainMapper.map(it))
            }
        }
    }
    fun deleteItemFromUi(position: Int) {
        val newList = _state.value.movies.toMutableList()
        val  tempMovie = newList[position]
        newList.removeAt(position)
        _state.update {
            it.copy(
                movies = newList,
                tempMovie = tempMovie
            )
        }
    }

    fun addItemToUI(position: Int) {
        val newList = _state.value.movies.toMutableList()
       state.value.tempMovie?.let { newList.add(position, it) }
        _state.update {
            it.copy(
                movies = newList
            )
        }
    }

}
