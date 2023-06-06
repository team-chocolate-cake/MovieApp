package com.chocolatecake.movieapp.ui.search.view

import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.data.repository.base.NoNetworkThrowable
import com.chocolatecake.movieapp.domain.usecases.genres.GetAllGenresMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search.SearchMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.InsertSearchHistoryUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.SearchHistoryUseCase
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
    private val getAllGenresMoviesUseCase: GetAllGenresMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val genreUiStateMapper: GenreUiStateMapper,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val searchHistoryUseCase: SearchHistoryUseCase,
) : BaseViewModel(), SearchListener {

    private val _state = MutableStateFlow(SearchUiState())
    val state = _state.asStateFlow()
    private val _event = Channel<SearchUiEvent>()
    val event = _event.receiveAsFlow()

    private suspend fun getAllGenresMovies() {
        _state.update { it.copy(isLoading = true) }
        try {
            _state.update {
                val updatedGenres =
                    getAllGenresMoviesUseCase()?.map { genreUiStateMapper.map(it) }?.map { genre ->
                        genre.copy(isSelected = genre.genreId == it.selectedMovieGenresId)
                    }
                it.copy(
                    genresMovies = updatedGenres,
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
            saveSearchHistoryInLocal(query)
            getSearchHistory(query)
            getData()
        }
    }

    private suspend fun getSearchHistory(query: String) {
        val result = searchHistoryUseCase(query)
        _state.update { it.copy(searchHistory = result) }
    }

    override fun getData() {
        onSearchForMovie()
    }

    private suspend fun saveSearchHistoryInLocal(query: String) {
        _state.update { it.copy(isLoading = true) }
        val searchHistory = SearchHistoryEntity(keyword = query)
        insertSearchHistoryUseCase(searchHistory)
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
                showErrorWithSnackBar("No Network")
                _state.update { it.copy(isLoading = false) }
            }
        }
    }

    override fun onClickFilter() {
        viewModelScope.launch {
            getAllGenresMovies()
            _event.send(SearchUiEvent.FilterEvent)
        }
    }

    override fun onClickGenre(genresId: Int) {
        val updatedGenres = _state.value.genresMovies?.map { genre ->
            genre.copy(isSelected = genre.genreId == genresId)
        }
        _state.update {
            it.copy(
                selectedMovieGenresId = genresId,
                isLoading = false,
                genresMovies = updatedGenres
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        viewModelScope.launch {
            _event.send(SearchUiEvent.ShowSnackBar(messages))
        }
    }
}