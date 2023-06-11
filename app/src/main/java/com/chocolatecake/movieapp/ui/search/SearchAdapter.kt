package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.R
import com.chocolatecake.movieapp.domain.model.Movie
import com.chocolatecake.movieapp.ui.base.BaseAdapter

class SearchAdapter(private val list: List<Movie>, private val listener: SearchListener) :
    BaseAdapter<Movie>(list,listener) {
    override val layoutID: Int = R.layout.search_item_movie
}