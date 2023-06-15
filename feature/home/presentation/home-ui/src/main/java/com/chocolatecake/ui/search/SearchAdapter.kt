package com.chocolatecake.ui.search

import com.chocolatecake.bases.BaseAdapter
import com.chocolatecake.ui.home.R
import com.chocolatecake.viewmodel.search.SearchListener
import com.chocolatecake.viewmodel.search.SearchUiState

class SearchAdapter(
    list: List<SearchUiState.MoviesUiState>,
    listener: SearchListener
) : BaseAdapter<SearchUiState.MoviesUiState>(list, listener) {
    override val layoutID: Int = R.layout.search_item_movie
}