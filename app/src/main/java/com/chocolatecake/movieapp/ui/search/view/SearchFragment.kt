package com.chocolatecake.movieapp.ui.search.view

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.databinding.FragmentSearchBinding
import com.chocolatecake.movieapp.ui.base.BaseFragment
import com.chocolatecake.movieapp.ui.search.SearchAdapter
import com.chocolatecake.movieapp.ui.search.ui_state.SearchUiEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment: BaseFragment<FragmentSearchBinding>() {
    override val layoutIdFragment: Int = R.layout.fragment_search
    override val viewModel by activityViewModels<SearchViewModel>()
    private lateinit var searchAdapter: SearchAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchAdapter = SearchAdapter(mutableListOf(), viewModel)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.state.collect{
                    searchAdapter.setItems(it.searchMovieResult)
                }
            }
        }
        binding.recyclerViewSearch.adapter = searchAdapter
        handleEvent()
        setupSearchHistoryAdapter()
    }

    private fun handleEvent() {
        lifecycleScope.launch { viewModel.event.collect { onEvent(it) } }
    }

    private fun onEvent(uiEvent: SearchUiEvent) {
        when (uiEvent) {
            is SearchUiEvent.FilterEvent -> showBottomSheet()
            is SearchUiEvent.ApplyFilterEvent -> applyFilter(uiEvent.genre)
            is SearchUiEvent.ShowSnackBar -> showSnackBar()
        }
    }

    private fun showSnackBar() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect {
                viewModel.showErrorWithSnackBar()
                Snackbar.make(binding.root, it.error?.first().toString(), Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun applyFilter(genresId: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.onClickGenre(genresId)
        }
    }

    private fun showBottomSheet() {
        FilterMovieBottomSheetFragment().show(childFragmentManager, "BOTTOM")
    }

    private fun setupSearchHistoryAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { uiState ->
                val searchHistory = uiState.searchHistory.map { it }
                val adapter = ArrayAdapter(
                    requireActivity(),
                    android.R.layout.simple_dropdown_item_1line,
                    searchHistory
                )
                binding.edittextSearch.setAdapter(adapter)
            }
        }
    }
}