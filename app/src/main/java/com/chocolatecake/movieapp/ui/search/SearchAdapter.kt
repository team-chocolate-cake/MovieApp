package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.domain.entities.MovieEntity
import com.chocolatecake.movieapp.ui.base.BaseAdapter
import com.chocolatecake.movieapp.ui.search.ui_state.SearchListener

class SearchAdapter(private val list: List<MovieEntity>, private val listener: SearchListener) :
    BaseAdapter<MovieEntity>(list,listener) {
    override val layoutID: Int = R.layout.search_item_movie
}