package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class SearchAdapter(private val list: List<SearchUiState.MediaUIState>, private val listener: SearchListener) :
    BaseAdapter<SearchUiState.MediaUIState>(list,listener) {
    override val layoutID: Int = R.layout.item_movie_horizontal
}