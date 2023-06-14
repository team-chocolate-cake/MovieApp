package com.chocolatecake.viewmodel.search

import com.chocolatecake.bases.BaseInteractionListener

interface SearchListener: BaseInteractionListener {
    fun onClickFilter()
    fun onClickGenre(genresId: Int)
}