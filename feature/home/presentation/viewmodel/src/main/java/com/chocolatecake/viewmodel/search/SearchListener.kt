package com.chocolatecake.viewmodel.search

import com.chocolatecake.viewmodel.common.listener.MovieListener
import com.chocolatecake.viewmodel.common.listener.PeopleListener

interface SearchListener:  MovieListener, PeopleListener {
    fun onClickFilter()
    fun onClickGenre(genresId: Int)
    fun onClickClear()
    fun showResultMovie()
    fun showResultTv()
    fun showResultPeople()

}