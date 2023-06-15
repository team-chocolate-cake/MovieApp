package com.chocolatecake.viewmodel.search

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.entities.GenreEntity
import com.chocolatecake.usecase.GetAllGenresMoviesUseCase
import com.chocolatecake.usecase.GetAllGenresTvsUseCase
import com.chocolatecake.usecase.search.SearchMoviesUseCase
import com.chocolatecake.usecase.search.SearchPeopleUseCase
import com.chocolatecake.usecase.search.SearchTvsUseCase
import com.chocolatecake.usecase.search_history.InsertSearchHistoryUseCase
import com.chocolatecake.usecase.search_history.SearchHistoryUseCase
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import com.chocolatecake.viewmodel.common.model.PeopleUIState
import com.chocolatecake.viewmodel.search.mappers.GenreUiStateMapper
import com.chocolatecake.viewmodel.search.mappers.MovieUiMapper
import com.chocolatecake.viewmodel.search.mappers.PeopleUiMapper
import com.chocolatecake.viewmodel.search.mappers.TvUiMapper
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
    private val getAllGenresTvsUseCase: GetAllGenresTvsUseCase,
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val searchTvsUseCase: SearchTvsUseCase,
    private val searchPeopleUseCase: SearchPeopleUseCase,
    private val insertSearchHistoryUseCase: InsertSearchHistoryUseCase,
    private val searchHistoryUseCase: SearchHistoryUseCase,
    private val genreUiStateMapper: GenreUiStateMapper,
    private val movieUiMapper: MovieUiMapper,
    private val tvUiMapper: TvUiMapper,
    private val peopleUiMapper: PeopleUiMapper
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
        insertSearchHistoryUseCase(query)
    }

    private suspend fun getSearchHistory(query: String) {
        val result = searchHistoryUseCase(query)
        _state.update { it.copy(searchHistory = result) }
    }


    fun getData() {
        when (_state.value.mediaType) {
            SearchUiState.SearchMedia.MOVIE -> onSearchForMovie()
            SearchUiState.SearchMedia.TV -> onSearchForTv()
            SearchUiState.SearchMedia.PEOPLE -> onSearchForPeople()
        }
    }

    fun onSearchForPeople() {
        tryToExecute(
            call = { searchPeopleUseCase(query.value) },
            mapper = peopleUiMapper,
            onSuccess = ::onSuccessPeople,
            onError = ::onError
        )
    }

    private fun onSuccessPeople(people: List<PeopleUIState>) {
        _state.update {
            it.copy(
                mediaType = SearchUiState.SearchMedia.PEOPLE,
                searchMediaResult = SearchItem.PeopleItem(people),
                isSelectedPeople = true,
                isLoading = false,
                error = null
            )
        }
    }

    fun onSearchForTv() {
        tryToExecute(
            call = { searchTvsUseCase(query.value) },
            mapper = tvUiMapper,
            onSuccess = ::onSuccessTv,
            onError = ::onError
        )
    }

    private fun onSuccessTv(tv: List<MovieHorizontalUIState>) {
        _state.update {
            it.copy(
                mediaType = SearchUiState.SearchMedia.TV,
                searchMediaResult = SearchItem.MediaItem(tv),
                isSelectedPeople = false,
                isLoading = false,
                error = null
            )
        }
    }

    fun onSearchForMovie() {
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

    private fun onSuccessMovies(mediaUIState: List<MovieHorizontalUIState>) {
        _state.update {
            it.copy(
                mediaType = SearchUiState.SearchMedia.MOVIE,
                searchMediaResult = SearchItem.MediaItem(mediaUIState),
                isSelectedPeople = false,
                isLoading = false,
                error = null
            )
        }
    }

    ///region events
    override fun onClickFilter() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            when (_state.value.mediaType) {
                SearchUiState.SearchMedia.MOVIE -> getAllGenresMovies()
                else -> getAllGenresTv()
            }
            _event.emit(SearchUiEvent.OpenFilterBottomSheet)
        }
    }

    private suspend fun getAllGenresTv() {
        tryToExecute(
            call = { getAllGenresTvsUseCase() },
            onSuccess = ::onSuccessGenres,
            onError = ::onError
        )
    }

    private suspend fun getAllGenresMovies() {
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

    override fun showResultMovie() {
        sendEvent(SearchUiEvent.ShowMovieResult)
    }

    override fun showResultTv() {
        sendEvent(SearchUiEvent.ShowTvResult)
    }

    override fun showResultPeople() {
        sendEvent(SearchUiEvent.ShowPeopleResult)
    }

    override fun onClickMovie(id: Int) {
        sendEvent(SearchUiEvent.NavigateToMovie(id))
    }

    override fun onClickPeople(id: Int) {
        sendEvent(SearchUiEvent.NavigateToPeople(id))
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