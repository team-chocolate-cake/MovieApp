package com.chocolatecake.viewmodel.people

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.GetMoviesByPeopleUseCase
import com.chocolatecake.usecase.GetPeopleDetailsUseCase
import com.chocolatecake.usecase.GetTvShowsByPeopleUseCase
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
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
    private val getMoviesByPeopleUseCase: GetMoviesByPeopleUseCase,
    private val getTvShowsByPeopleUseCase: GetTvShowsByPeopleUseCase,
    private val peopleDataUiMapper: PeopleDataUiMapper,
    private val moviesByPeopleUiMapper: MoviesByPeopleUiMapper,
    private val tvShowsByPeopleUiMapper: TvShowsByPeopleUiMapper

) : BaseViewModel<PeopleDetailsUiState, PeopleDetailsUiEvent>(PeopleDetailsUiState()),
    PeopleDetailsListener {

    var num_people_movies = ""

    fun getPersonData(personId: Int) {
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

    private fun onSuccessGetPersonData(peopleDataUiState: PeopleDetailsUiState.PeopleDataUiState) {
        _state.update {
            it.copy(
                peopleData = peopleDataUiState,
                isLoading = false
            )
        }
        Log.e("num_people_movies", num_people_movies)
    }

    private fun onErrorGetPersonData(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    fun getMoviesByPeople(personId: Int) {
        tryToExecute(
            call = { getMoviesByPeopleUseCase.invoke(personId) },
            onSuccess = ::onSuccessGetMoviesByPeople,
            onError = ::onErrorGetMoviesByPeople,
            mapper = moviesByPeopleUiMapper
        )
    }


    private fun onSuccessGetMoviesByPeople(moviesUiState: List<PeopleDetailsUiState.PeopleMediaUiState>) {
        _state.update {
            it.copy(
                Movies = moviesUiState,
                isLoading = false,
            )
        }
        num_people_movies = moviesUiState.size.toString()
        Log.e("num_people_movies", num_people_movies)
    }

    private fun onErrorGetMoviesByPeople(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }


    fun getTvShowsByPeople(personId: Int) {
        tryToExecute(
            call = { getTvShowsByPeopleUseCase(personId) },
            onSuccess = ::onSuccessGetTvShowsByPeople,
            onError = ::onErrorGetTvShowsByPeople,
            mapper = tvShowsByPeopleUiMapper
        )
    }


    private fun onSuccessGetTvShowsByPeople(tvShowsUiState: List<PeopleDetailsUiState.PeopleMediaUiState>) {
        _state.update {
            it.copy(
                TvShows = tvShowsUiState,
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


