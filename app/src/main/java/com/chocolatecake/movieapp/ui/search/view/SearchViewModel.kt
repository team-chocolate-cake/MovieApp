package com.chocolatecake.movieapp.ui.search.view

import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.repository.base.NoNetworkThrowable
import com.chocolatecake.movieapp.domain.usecases.genres.GetAllGenresMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search.GetSearchMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search.SearchMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.GetSearchHistoryUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.InsertSearchHistoryUseCase
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import com.chocolatecake.movieapp.ui.search.GenreUiStateMapper
import com.chocolatecake.movieapp.ui.search.ui_state.SearchListener
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiEvent
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchMoviesUseCase: GetSearchMoviesUseCase,
    private val getAllGenresMoviesUseCase: GetAllGenresMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val genreUiStateMapper: GenreUiStateMapper,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
) : BaseViewModel(), SearchListener {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()
    private val _event = Channel<SearchUiEvent>()
    val event = _event.receiveAsFlow()

    private suspend fun saveSearchHistoryInLocal(query: String) {
        _state.update { it.copy(isLoading = true) }
        val searchHistory = SearchHistoryEntity(keyword = query)
        insertSearchHistoryUseCase(searchHistory)
    }

    override fun getData() {
        onSearchForMovie()
    }

    private suspend fun getAllGenresMovies() {
        _state.update { it.copy(isLoading = true) }

        try {
            _state.update {
                it.copy(
                    genresMovies = getAllGenresMoviesUseCase()?.map { genreUiStateMapper.map(it) },
                    isLoading = false,
                    error = null
                )
            }
        } catch (noNetwork: NoNetworkThrowable) {
            _state.update {
                it.copy(
                    error = listOf("No Network Connection"),
                    isLoading = false
                )
            }
        }
    }

    fun onSearchInputChanged(newQuery: CharSequence) {
        val query = newQuery.toString()
        _state.update { it.copy(query = query, isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            saveSearchHistoryInLocal(newQuery.toString())
            getData()
            getSearchHistory(query)
        }
    }

    private fun onSearchForMovie() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        searchMovieResult = searchMoviesUseCase(it.query, it.selectedMovieGenresId),
                        isLoading = false,
                        error = null
                    )
                }
            } catch (noNetwork: NoNetworkThrowable) {
                _state.update { it.copy(error = listOf("No Network"), isLoading = false) }
            }
        }
    }

    private suspend fun getSearchHistory(query: String) {
        val result = getSearchHistoryUseCase(query)
        _state.update { it.copy(searchHistory = result) }
    }

    override fun onClickFilter() {
        viewModelScope.launch {
            _event.send(SearchUiEvent.FilterEvent)
            getAllGenresMovies()

        }
    }

    override fun onClickGenre(genresId: Int) {
        _state.update {
            it.copy(
                selectedMovieGenresId = genresId,
                isLoading = false
            )
        }
    }
}