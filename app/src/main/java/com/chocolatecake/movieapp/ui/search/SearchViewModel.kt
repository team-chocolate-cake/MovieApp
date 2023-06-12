package com.chocolatecake.movieapp.ui.search

import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.data.local.database.entity.SearchHistoryEntity
import com.chocolatecake.movieapp.domain.model.Genre
import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.domain.model.TvEntity
import com.chocolatecake.movieapp.domain.usecases.genres.GetAllGenresMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search.SearchMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.search.SearchTvUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.InsertSearchHistoryUseCase
import com.chocolatecake.movieapp.domain.usecases.search_history.SearchHistoryUseCase
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
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
    private val searchTvUseCase: SearchTvUseCase
) : BaseViewModel<SearchUiState, SearchUiEvent>(), SearchListener {
    override fun initialState() = SearchUiState()

    init {
        viewModelScope.launch {
            var oldValue = ""
            onSearchInputChanged(state.value.query)
            state.debounce(1000)
                .filter { it.query.isNotEmpty() && oldValue != state.value.query }
                .collect { value ->
                    onSearchInputChanged(state.value.query)
                    oldValue = state.value.query
                }
        }
    }

    fun setSearchQuery(query: CharSequence?) {
        _state.update { it.copy(query = query.toString()) }
    }


    private fun onSearchInputChanged(newQuery: CharSequence) {
        val query = newQuery.toString()
        _state.update { it.copy(query = query, isLoading = true) }
        viewModelScope.launch(Dispatchers.IO) {
            saveSearchHistoryInLocal(query)
            getSearchHistory(query)
            getData()
        }
    }

    private suspend fun saveSearchHistoryInLocal(query: String) {
        _state.update { it.copy(isLoading = true) }
        val searchHistory = SearchHistoryEntity(keyword = query)
        insertSearchHistoryUseCase(searchHistory)
    }

    private suspend fun getSearchHistory(query: String) {
        val result = searchHistoryUseCase(query)
        _state.update { it.copy(searchHistory = result) }
    }



    override fun getData() {
//       Mediia.MOVIE -> onSearchForMovie()
//       Media.Tv ->  onSearchForTv()
    }

    private fun onSearchForTv() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = {searchTvUseCase(_state.value.query)},
            onSuccess = ::onSuccessTv,
            onError = ::onError
        )
    }

    private fun onSuccessTv(tv: List<TvEntity>) {
        _state.update {
            it.copy(
                searchTvShowResult = tv,
                isLoading = false,
                error = null
            )
        }
    }

    private fun onSearchForMovie() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { searchMoviesUseCase(_state.value.query, _state.value.selectedMovieGenresId) },
            onSuccess = ::onSuccessMovies,
            onError = ::onError
        )
    }

    private fun onSuccessMovies(movies: List<Movie>) {
        _state.update {
            it.copy(
                searchMovieResult = movies,
                isLoading = false,
                error = null
            )
        }
    }


    ///region events
    override fun onClickFilter() {
        viewModelScope.launch {
            getAllGenresMovies()
            _event.send(SearchUiEvent.FilterEvent)
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

    private fun onSuccessGenres(genres: List<Genre>) {
        _state.update {
            val updatedGenres =
                genres.map { genre ->
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
        viewModelScope.launch {
            _event.send(SearchUiEvent.ShowSnackBar(messages))
        }
    }
    //endregion
}