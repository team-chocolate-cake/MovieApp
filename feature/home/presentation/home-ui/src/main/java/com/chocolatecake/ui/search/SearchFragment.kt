package com.chocolatecake.ui.search

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.home.R
import com.chocolatecake.ui.home.databinding.FragmentSearchBinding
import com.chocolatecake.viewmodel.search.SearchItem
import com.chocolatecake.viewmodel.search.SearchUiEvent
import com.chocolatecake.viewmodel.search.SearchUiState
import com.chocolatecake.viewmodel.search.SearchViewModel
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, SearchUiState, SearchUiEvent>() {

    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel by activityViewModels<SearchViewModel>()

    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupHomeAdapter()
        collectChange()
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val chip = group.findViewById<Chip>(checkedId)
            if (chip?.isChecked == true) {
                // Do nothing when the same chip is reselected
            }
        }
    }

    private fun setupHomeAdapter() {
        searchAdapter = SearchAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSearch.adapter = searchAdapter
    }

    private fun collectChange() {
        collectLatest {
            viewModel.state.collect { state ->
                setupSearchHistoryAdapter(state)
                val searchItems = when (state.mediaType) {
                    SearchUiState.SearchMedia.MOVIE, SearchUiState.SearchMedia.TV -> {
                        state.searchMediaResult.map { SearchItem.MediaItem(it) }
                    }
                    SearchUiState.SearchMedia.PEOPLE -> {
                        state.searchPeopleResult.map { SearchItem.PeopleItem(it) }
                    }
                }
                searchAdapter.setItems(searchItems)
                state.error?.last()?.let { showSnackBar(it) }
            }
        }
    }

    private fun setupSearchHistoryAdapter(state: SearchUiState) {
        val searchHistory = state.searchHistory.map { it }
        val adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_dropdown_item_1line,
            searchHistory
        )
        binding.edittextSearch.setAdapter(adapter)
    }

    override fun onEvent(event: SearchUiEvent) {
        when (event) {
            is SearchUiEvent.OpenFilterBottomSheet -> showBottomSheet()
            is SearchUiEvent.ApplyFilter -> applyFilter(event.genre)
            is SearchUiEvent.ShowSnackBar -> showSnackBar(event.messages)
            is SearchUiEvent.NavigateToMovie -> navigateToMovie(event.movieId)
            is SearchUiEvent.NavigateToPeople -> navigateToPeople(event.peopleId)
            is SearchUiEvent.NavigateToTv -> navigateToTv(event.tvId)
            is SearchUiEvent.ShowMovieResult -> showMovieResult()
            is SearchUiEvent.ShowTvResult -> showTvResult()
            is SearchUiEvent.ShowPeopleResult -> showPeopleResult()
        }
    }

    private fun showBottomSheet() {
        FilterMovieBottomSheetFragment().show(childFragmentManager, "BOTTOM")
    }

    private fun applyFilter(genresId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onClickGenre(genresId)
        }
    }

    private fun navigateToMovie(movieId: Int) {
        findNavController().navigate(
            SearchFragmentDirections.actionSearchFragmentToMovieDetailsFragment(
                movieId
            )
        )
    }

    private fun navigateToPeople(peopleId: Int) {
        toastMessage("Navigate To People:", peopleId)
    }

    private fun navigateToTv(tvId: Int) {
        toastMessage("Navigate To Tv:", tvId)
    }

    private fun showMovieResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onSearchForMovie()
        }
    }

    private fun showTvResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onSearchForTv()
        }
    }

    private fun showPeopleResult() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onSearchForPeople()
        }
    }

    private fun toastMessage(message: String, id: Int) {
        Toast.makeText(
            binding.root.context,
            "$message $id",
            Toast.LENGTH_SHORT
        )
            .show()
    }
}