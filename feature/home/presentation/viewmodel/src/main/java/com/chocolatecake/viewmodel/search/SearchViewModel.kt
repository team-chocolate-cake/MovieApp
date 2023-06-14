package com.chocolatecake.viewmodel.search

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.usecase.GetAllGenresMoviesUseCase
import com.chocolatecake.usecase.search.SearchMoviesUseCase
import com.chocolatecake.usecase.search_history.InsertSearchHistoryUseCase
import com.chocolatecake.usecase.search_history.SearchHistoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getAllGenresMoviesUseCase: GetAllGenresMoviesUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val genreUiStateMapper: GenreUiStateMapper,
    private val movieUiMapper: MovieUiMapper,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val searchHistoryUseCase: SearchHistoryUseCase,
) : BaseViewModel<SearchUiState, SearchUiEvent>(SearchUiState()), SearchListener {

    val query = MutableStateFlow("")

    init {
        viewModelScope.launch {
            query.debounce(1000)
                .collect { onSearchInputChanged(it) }
        }
    }

    private fun onSearchInputChanged(newQuery: String) {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            saveSearchHistoryInLocal(newQuery)
            getSearchHistory(newQuery)
            getData()
        }
    }

    private suspend fun saveSearchHistoryInLocal(query: String) {
        _state.update { it.copy(isLoading = true) }
        insertSearchHistoryUseCase(query)
    }

    private suspend fun getSearchHistory(query: String) {
        val result = searchHistoryUseCase(query)
        _state.update { it.copy(searchHistory = result) }
    }


    fun getData() {
        onSearchForMovie()
    }

    private fun onSearchForMovie() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {
                searchMoviesUseCase(
                    query.value,
                    _state.value.selectedMovieGenresId
                )
            },
            mapper = movieUiMapper,
            onSuccess = ::onSuccessMovies,
            onError = ::onError,
        )
    }

    private fun onSuccessMovies(moviesUiStates: List<SearchUiState.MoviesUiState>) {
        _state.update {
            it.copy(
                searchMovieResultEntity = moviesUiStates,
                isLoading = false,
                error = null
            )
        }
    }


    ///region events
    override fun onClickFilter() {
        viewModelScope.launch {
            getAllGenresMovies()
            _event.emit(SearchUiEvent.FilterEvent)
        }
    }

    private suspend fun getAllGenresMovies() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getAllGenresMoviesUseCase() },
            onSuccess = ::onSuccessGenres,
            onError = ::onError
        )
    }

    private fun onSuccessGenres(genreEntities: List<GenreEntity>) {
        _state.update {
            val updatedGenres =
                genreEntities.map { genre ->
                    genreUiStateMapper.map(
                        genre,
                        genre.genreID == it.selectedMovieGenresId
                    )
                }
            it.copy(
                genresMovies = updatedGenres,
                isLoading = false,
                error = null
            )
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
    ///endregion


    /// region error handling
    private fun onError(throwable: Throwable) {
        showErrorWithSnackBar(throwable.message ?: "No Network Connection")
        _state.update {
            it.copy(
                error = listOf(throwable.message ?: "No Network Connection"),
                isLoading = false
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(SearchUiEvent.ShowSnackBar(messages))
    }
    //endregion
}