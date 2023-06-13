package com.chocolatecake.ui.search

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.chocolatecake.bases.BaseFragment
import com.chocolatecake.ui.R
import com.chocolatecake.ui.databinding.FragmentSearchBinding
import com.chocolatecake.viewmodel.search.SearchUiEvent
import com.chocolatecake.viewmodel.search.SearchUiState
import com.chocolatecake.viewmodel.search.SearchViewModel
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
    }

    private fun setupHomeAdapter() {
        searchAdapter = SearchAdapter(mutableListOf(), viewModel)
        binding.recyclerViewSearch.adapter = searchAdapter
    }

    override fun onSateChange(state: SearchUiState) {
        setupSearchHistoryAdapter(state)
        searchAdapter.setItems(state.searchMovieResultEntity)
        state.error?.last()?.let { showSnackBar(it) }
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
            is SearchUiEvent.FilterEvent -> showBottomSheet()
            is SearchUiEvent.ApplyFilterEvent -> applyFilter(event.genre)
            is SearchUiEvent.ShowSnackBar -> showSnackBar(event.messages)
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
}