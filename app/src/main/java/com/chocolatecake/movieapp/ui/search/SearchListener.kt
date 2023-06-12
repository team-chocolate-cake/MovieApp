package com.chocolatecake.movieapp.ui.search

import com.chocolatecake.movieapp.ui.base.BaseInteractionListener

interface SearchListener: BaseInteractionListener {
    fun onClickFilter()
    fun onClickGenre(genresId: Int)
    fun onClickMedia()
}