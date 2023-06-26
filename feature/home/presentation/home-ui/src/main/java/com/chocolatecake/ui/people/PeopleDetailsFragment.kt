package com.chocolatecake.ui.people

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.common.adapters.MediaVerticalAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentPeopleDetailsBinding
import com.chocolatecake.ui.people.adapter.PeopleDetailsRecyclerAdapter

import com.chocolatecake.viewmodel.people.PeopleDetailsUiEvent
import com.chocolatecake.viewmodel.people.PeopleDetailsUiState
import com.chocolatecake.viewmodel.people.PeopleDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PeopleDetailsFragment :
    BaseFragment<FragmentPeopleDetailsBinding, PeopleDetailsUiState, PeopleDetailsUiEvent>() {
    override val layoutIdFragment: Int
        get() = R.layout.fragment_people_details
    override val viewModel: PeopleDetailsViewModel by viewModels()

    private lateinit var peopleMoviesAdapter: PeopleDetailsRecyclerAdapter
    private lateinit var peopleTvShowsAdapter: PeopleDetailsRecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTvShowsByPeople(35)
        viewModel.getPersonData(35)
        viewModel.getMoviesByPeople(35)

        setAdapters()
        getData()
    }

    private fun setAdapters() {
        peopleMoviesAdapter = PeopleDetailsRecyclerAdapter(mutableListOf(), viewModel)
        binding.recyclerViewPeopleMovies.adapter = peopleMoviesAdapter
        peopleTvShowsAdapter = PeopleDetailsRecyclerAdapter(mutableListOf(), viewModel)
        binding.recyclerViewPeopleTvShows.adapter = peopleTvShowsAdapter


    }

    private fun getData() {
        collectLatest {
            viewModel.state.collect { state ->
                peopleMoviesAdapter.setItems(state.Movies)
                peopleTvShowsAdapter.setItems(state.TvShows)
                if (state.onErrors.isNotEmpty()) {
                    state.onErrors.last().let {
                        showSnackBar(it)
                    }
                }
            }
        }
    }

    override fun onEvent(event: PeopleDetailsUiEvent) {
        when (event) {
            PeopleDetailsUiEvent.BackNavigate -> findNavController().popBackStack()
            else -> {}
        }
    }

}