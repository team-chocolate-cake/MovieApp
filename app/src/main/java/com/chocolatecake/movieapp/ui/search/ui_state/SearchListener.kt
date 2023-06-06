package com.chocolatecake.movieapp.ui.search.ui_state

import com.chocolatecake.movieapp.ui.base.BaseInteractionListener

interface SearchListener: BaseInteractionListener {
    fun onClickFilter()
    fun onClickGenre(genresId: Int)
}