package com.chocolatecake.viewmodel.people

import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.usecase.people.GetMoviesByPeopleUseCase
import com.chocolatecake.usecase.people.GetPeopleDetailsUseCase
import com.chocolatecake.usecase.people.GetTvShowsByPeopleUseCase
import com.chocolatecake.viewmodel.common.listener.MediaListener
import com.chocolatecake.viewmodel.common.model.MediaVerticalUIState
import com.chocolatecake.viewmodel.home.TopRatedUiState
import com.chocolatecake.viewmodel.people.mapper.MoviesByPeopleUiMapper
import com.chocolatecake.viewmodel.people.mapper.PeopleDataUiMapper
import com.chocolatecake.viewmodel.people.mapper.TvShowsByPeopleUiMapper
import com.chocolatecake.viewmodel.search.MovieUiMapper
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
    MediaListener {



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
    }

    private fun onErrorGetPersonData(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }

    fun getMoviesByPeople(personId: Int) {
        tryToExecute(
            call =  {getMoviesByPeopleUseCase.invoke(personId)} ,
            onSuccess = ::onSuccessGetMoviesByPeople,
            onError = ::onErrorGetMoviesByPeople,
            mapper = moviesByPeopleUiMapper
        )
    }


    private fun onSuccessGetMoviesByPeople(moviesUiState: List<MediaVerticalUIState>) {
        _state.update {
            it.copy(
                Movies = moviesUiState,
                isLoading = false
            )
        }
    }

    private fun onErrorGetMoviesByPeople(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }


    fun getTvShowsByPeople(personId: Int) {
        tryToExecute(
            call = {getTvShowsByPeopleUseCase(personId)} ,
            onSuccess = ::onSuccessGetTvShowsByPeople,
            onError = ::onErrorGetTvShowsByPeople,
            mapper = tvShowsByPeopleUiMapper
        )
    }


    private fun onSuccessGetTvShowsByPeople(tvShowsUiState: List<MediaVerticalUIState>) {
        _state.update {
            it.copy(
                TvShows =tvShowsUiState ,
                isLoading = false
            )
        }
    }

    private fun onErrorGetTvShowsByPeople(e: Throwable) {
        val errors = _state.value.onErrors.toMutableList()
        errors.add(e.message.toString())
        _state.update { it.copy(onErrors = errors, isLoading = false) }
    }


    override fun onClickMedia(id: Int) {
        TODO("Not yet implemented")
    }
}