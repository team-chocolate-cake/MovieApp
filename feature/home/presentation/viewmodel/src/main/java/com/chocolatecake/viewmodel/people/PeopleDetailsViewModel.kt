package com.chocolatecake.viewmodel.people

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.GetMoviesByPersonUseCase
import com.chocolatecake.usecase.GetPeopleDetailsUseCase
import com.chocolatecake.usecase.GetTvShowsByPersonUseCase
import com.chocolatecake.viewmodel.people.mapper.MoviesByPeopleUiMapper
import com.chocolatecake.viewmodel.people.mapper.PeopleDataUiMapper
import com.chocolatecake.viewmodel.people.mapper.TvShowsByPeopleUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleDetailsViewModel @Inject constructor(
    private val getPeopleDetailsUseCase: GetPeopleDetailsUseCase,
    private val getMoviesByPersonUseCase: GetMoviesByPersonUseCase,
    private val getTvShowsByPersonUseCase: GetTvShowsByPersonUseCase,
    private val peopleDataUiMapper: PeopleDataUiMapper,
    private val moviesByPeopleUiMapper: MoviesByPeopleUiMapper,
    private val tvShowsByPeopleUiMapper: TvShowsByPeopleUiMapper,
    savedStateHandle: SavedStateHandle,

    ) : BaseViewModel<PersonDetailsUiState, PeopleDetailsUiEvent>(PersonDetailsUiState()),
    PeopleDetailsListener {
    private val personId =
        savedStateHandle.get<Int>("personId") ?: 3

    init {
        refreshScreen()
    }
    fun refreshScreen() {
        getPersonData()
        getMoviesByPeople()
        getTvShowsByPeople()
        _state.update { it.copy(onErrors = emptyList(), isLoading = true) }
    }

    fun getPersonData() {
        viewModelScope.launch {
            try {
                val data = getPeopleDetailsUseCase(personId)
                val peopleData = peopleDataUiMapper.map(data)
                onSuccessGetPersonData(peopleData)
            } catch (e: Throwable) {
                onErrorGetPersonData(e)
            }

        }

    }

    private fun onSuccessGetPersonData(personInfoUiState: PersonDetailsUiState.PersonInfoUiState) {
        _state.update {
            it.copy(
                peopleData = personInfoUiState,
                isLoading = false
            )
        }
    }

    private fun onErrorGetPersonData(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    fun getMoviesByPeople() {
        tryToExecute(
            call = { getMoviesByPersonUseCase.invoke(personId) },
            onSuccess = ::onSuccessGetMoviesByPeople,
            onError = ::onErrorGetMoviesByPeople,
            mapper = moviesByPeopleUiMapper
        )
    }


    private fun onSuccessGetMoviesByPeople(list: List<PersonDetailsUiState.PeopleMediaUiState>) {
        _state.update {
            it.copy(
                movies = list,
                isLoading = false,
            )
        }
    }

    private fun onErrorGetMoviesByPeople(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }


    fun getTvShowsByPeople() {
        tryToExecute(
            call = { getTvShowsByPersonUseCase(personId) },
            onSuccess = ::onSuccessGetTvShowsByPeople,
            onError = ::onErrorGetTvShowsByPeople,
            mapper = tvShowsByPeopleUiMapper
        )
    }


    private fun onSuccessGetTvShowsByPeople(list: List<PersonDetailsUiState.PeopleMediaUiState>) {
        _state.update {
            it.copy(
                tvShows = list,
                isLoading = false
            )
        }
    }

    private fun onErrorGetTvShowsByPeople(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    override fun onClickMedia(itemId: Int, name: String) {
        if (name == "movies") {
            sendEvent(PeopleDetailsUiEvent.ClickMovieEvent(itemId))
        } else if (name == "tvShows") {
            sendEvent(PeopleDetailsUiEvent.ClickTvShowsEvent(itemId))
        }
    }

    override fun backNavigate() {
        sendEvent(PeopleDetailsUiEvent.BackNavigate)
    }
}


